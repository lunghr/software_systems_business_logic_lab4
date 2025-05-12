package com.example.service


import com.example.kafka.CartServiceProducer
import com.example.kafka.ResponseStorage
import com.example.model.CartServiceException
import com.example.model.Order
import com.example.model.OrderItem
import com.example.repos.OrderItemRepository
import com.example.repos.OrderRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import service.JwtService
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val responseStorage: ResponseStorage,
    private val cartServiceProducer: CartServiceProducer,
    private val jwtService: JwtService
) {

    private fun getListOfItems(order: Order): List<OrderItem> {
        val userId = order.userId
        val correlationId = UUID.randomUUID()
        cartServiceProducer.getCart(userId, correlationId)

        val response = responseStorage.waitForResponse(correlationId.toString())
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


    fun createOrder(token: String): ResponseEntity<Any> {
        val userId = getUserId(token)
        val order = Order(userId = userId)
        val items = getListOfItems(order)
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cart is empty")
        }
        order.items = items
        order.totalPrice = items.sumOf { item -> item.price * item.quantity }
        orderRepository.save(order)
        cartServiceProducer.bookOrder(userId)
        return ResponseEntity.status(HttpStatus.CREATED).body(order.id)
    }

    @Transactional
    fun delete(order: Order) {
        orderRepository.delete(order)
    }
}