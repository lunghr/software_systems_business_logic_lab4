package com.example.service


import com.example.kafka.BankServiceProducer
import com.example.kafka.ResponseStorage
import com.example.kafka.tmp.PaymentTransactionResponse
import com.example.model.PaymentTransaction
import com.example.model.enums.TransactionStatus
import com.example.repos.PaymentTransactionRepository
import model.TransactionDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TransactionService(
    private val transactionRepository: PaymentTransactionRepository,
    private val paymentMethodService: PaymentMethodService,
    private val bankServiceProducer: BankServiceProducer,
    private val responseStorage: ResponseStorage
) {
    @Transactional
    fun processTransaction(transactionDtoRequest: TransactionDto): TransactionDto {
        val userId = transactionDtoRequest.userId
        val amount = transactionDtoRequest.transactionAmount
        val orderId = transactionDtoRequest.orderId
        val transactionDtoResponse = TransactionDto(
            cardExists = false,
            enoughMoney = false,
            orderId = orderId,
            userId = userId,
            transactionAmount = amount
        )
        val paymentMethod = paymentMethodService.getFirstPaymentMethodByUserId(userId)?.let {
            it.also {
                transactionDtoResponse.cardExists = true
            }
        } ?: return transactionDtoResponse

        val transaction = transactionRepository.save(
            PaymentTransaction(
                orderId = orderId,
                transactionStatus = TransactionStatus.PENDING,
                transactionAmount = amount,
                paymentMethod = paymentMethod
            )
        )
        val correlationId = transaction.id

        bankServiceProducer.startPaymentTransaction(
            cardNumber = paymentMethod.cardNumber,
            transactionAmount = amount,
            correlationId = correlationId
        )

        val response =
            responseStorage.waitForResponse(correlationId.toString(), 5, PaymentTransactionResponse::class.java)
        response?.let {
            transactionDtoResponse.enoughMoney = it.isSuccess
            transaction.transactionStatus = if (it.isSuccess) {
                TransactionStatus.COMPLETED
            } else {
                TransactionStatus.FAILED
            }
        } ?: return transactionDtoResponse

        transactionRepository.updateTransactionStatus(
            transactionId = transaction.id,
            transactionStatus = transaction.transactionStatus
        )

        return transactionDtoResponse
    }
}