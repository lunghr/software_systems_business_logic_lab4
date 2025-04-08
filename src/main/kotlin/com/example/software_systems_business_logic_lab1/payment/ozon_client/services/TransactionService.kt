package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.models.enums.OrderPaymentStatus
import com.example.software_systems_business_logic_lab1.application.services.OrderService
import com.example.software_systems_business_logic_lab1.payment.bank.services.BankService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentTransaction
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus.Companion.toBoolean
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class TransactionService(
    private val transactionRepository: PaymentTransactionRepository,
    private val paymentMethodService: PaymentMethodService,
    private val orderService: OrderService,
    private val bankService: BankService
) {
    @Transactional
    fun processTransaction(paymentMethodId: UUID, orderId: UUID): PaymentTransaction {
        val paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId)
            ?: throw IllegalArgumentException("Payment method not found")

        val order = orderService.getOrderById(orderId)
            ?: throw IllegalArgumentException("Order not found")

        val transaction = transactionRepository.save(
            toTransaction(
                paymentMethod = paymentMethod,
                order = order,
                amount = order.totalPrice
            )
        )

        val status = bankService.processTransaction(
            transactionAmount = transaction.transactionAmount,
            paymentData = paymentMethod.ozonPaymentData
        )

        val isSuccess = toBoolean(status)
        transaction.transactionStatus = if (isSuccess) {
            status.also { orderService.changeOrderStatus(order, OrderPaymentStatus.PAID) }
        } else {
            TransactionStatus.FAILED.also { orderService.changeOrderStatus(order, OrderPaymentStatus.REFUND) }
        }

        return transactionRepository.save(transaction)
    }
}