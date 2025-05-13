package com.example.service


import com.example.kafka.CartServiceProducer
import com.example.kafka.PaymentServiceProducer
import com.example.kafka.ResponseStorage
import com.example.kafka.tmp.ItemsResponse
import com.example.model.*
import com.example.repos.OrderRepository
import model.TransactionDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import service.EmailService
import service.JwtService
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val responseStorage: ResponseStorage,
    private val cartServiceProducer: CartServiceProducer,
    private val jwtService: JwtService,
    private val paymentServiceProducer: PaymentServiceProducer,
    private val orderSchedulerService: OrderSchedulerService,
    private val emailService: EmailService
) {


    private fun getListOfItems(order: Order): List<OrderItem> {
        val userId = order.userId
        val correlationId = UUID.randomUUID()
        cartServiceProducer.getCart(userId, correlationId)

        val response = responseStorage.waitForResponse(correlationId.toString(), 5, ItemsResponse::class.java)
        response?.let {
            val items = it.items.map { item ->
                OrderItem(
                    productId = item.productId,
                    quantity = item.quantity,
                    price = item.price,
                    order = order
                )
            }
            return items
        } ?: throw CartServiceException()
    }

    private fun getUserId(token: String): UUID {
        return jwtService.extractId(jwtService.extractToken(token))
    }

    private fun getUserEmail(token: String): String {
        return jwtService.extractEmail(jwtService.extractToken(token))
    }


    @Transactional
    fun createOrder(token: String): ResponseEntity<Any> {
        val userId = getUserId(token)
        val userEmail = getUserEmail(token)
        val order = Order(userId = userId)
        val items = getListOfItems(order)
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cart is empty")
        }
        order.items = items
        order.totalPrice = items.sumOf { item -> item.price * item.quantity }
        orderRepository.save(order)
        cartServiceProducer.bookOrder(userId)
        orderSchedulerService.scheduledOrderCancellation(order.id, userEmail)
        emailService.sendCreatedOrderEmail(
            toEmail = userEmail,
            orderId = order.id.toString(),
            totalPrice = order.totalPrice,
            paymentWindow = "$30 seconds"
        )
        return ResponseEntity.status(HttpStatus.OK).body("Order created successfully: ${order.id}")
    }

    private fun getOrderById(id: UUID): Order {
        return orderRepository.findById(id).orElseThrow { OrderNotFoundException() }
    }


    @Transactional
    fun payOrder(token: String, orderId: UUID): ResponseEntity<Any> {
        val order = getOrderById(orderId)
        val userId = order.userId
        val correlationId = UUID.randomUUID()
        val userEmail = getUserEmail(token)
        paymentServiceProducer.startTransaction(
            userId = userId,
            orderId = order.id,
            transactionAmount = order.totalPrice,
            correlationId = correlationId
        )

        val response = responseStorage.waitForResponse(correlationId.toString(), 5, TransactionDto::class.java)
        response?.let {
            if (!it.cardExists) {
                orderRepository.updateOrderStatus(
                    id = order.id,
                    status = OrderStatus.CANCELLED
                )
                cartServiceProducer.unbookOrder(userId)
                emailService.sendCancelledOrderEmail(
                    toEmail = userEmail
                )
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card does not exist")
            }
            if (!it.enoughMoney) {
                orderRepository.updateOrderStatus(
                    id = order.id,
                    status = OrderStatus.CANCELLED
                )
                cartServiceProducer.unbookOrder(userId)
                emailService.sendCancelledOrderEmail(
                    toEmail = userEmail
                )
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough money")
            } else {
                orderRepository.updateOrderStatus(
                    id = order.id,
                    status = OrderStatus.PAID
                )
                cartServiceProducer.clearCart(userId)
            }
        } ?: return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment service is not available")
        emailService.sendPaidOrderEmail(
            toEmail = userEmail,
            orderId = order.id.toString()
        )
        return ResponseEntity.status(HttpStatus.OK).body("Order paid successfully")
    }

    fun cancelOrder(orderId: UUID, userEmail: String) {
        val order = getOrderById(orderId)
        if (order.status == OrderStatus.WAITING_FOR_PAYMENT) {
            orderRepository.updateOrderStatus(
                id = order.id,
                status = OrderStatus.CANCELLED
            )
            cartServiceProducer.unbookOrder(order.userId)
            emailService.sendCancelledOrderEmail(
                toEmail = userEmail
            )
        }
    }


}