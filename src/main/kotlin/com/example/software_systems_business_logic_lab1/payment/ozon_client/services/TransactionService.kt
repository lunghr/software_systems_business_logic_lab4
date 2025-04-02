package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.models.enums.OrderPaymentStatus
import com.example.software_systems_business_logic_lab1.application.services.OrderService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentTransaction
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus.Companion.toBoolean
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.UUID


@Service
class TransactionService(
    private val transactionRepository: PaymentTransactionRepository,
    private val paymentMethodService: PaymentMethodService,
    private val orderService: OrderService,
    private val restTemplate: RestTemplate
) {
//    private fun createTransaction(paymentMethod: PaymentMethod, order: Order): PaymentTransaction {
//        return transactionRepository.save(
//            toTransaction(
//                paymentMethod = paymentMethod,
//                order = order,
//                amount = order.totalPrice
//            )
//        )
//    }
    @Transactional
    fun processTransaction(paymentMethodId: UUID, orderId: UUID): PaymentTransaction {
        val paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId)
            ?: throw IllegalArgumentException("Payment method not found")

        var order = orderService.getOrderById(orderId)
            ?: throw IllegalArgumentException("Order not found")

        val transaction = transactionRepository.save(
            toTransaction(
                paymentMethod = paymentMethod,
                order = order,
                amount = order.totalPrice
            )
        )

        val bankPaymentUrl = "http://localhost:8080/bank/pay/${transaction.transactionAmount}"

        val status = restTemplate.postForObject(
            bankPaymentUrl, paymentMethod.ozonPaymentData,
            TransactionStatus::class.java
        ) ?: throw RuntimeException("Failed to process payment")

        val isSuccess = toBoolean(status)
        transaction.transactionStatus = if (isSuccess) {
            status.also { orderService.changeOrderStatus(order, OrderPaymentStatus.PAID) }
        } else {
            TransactionStatus.FAILED.also { orderService.changeOrderStatus(order, OrderPaymentStatus.REFUND) }
        }

        return transactionRepository.save(transaction)
    }


}