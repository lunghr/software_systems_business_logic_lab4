package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import model.TransactionDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PaymentServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun startTransaction(
        orderId: UUID,
        userId: UUID,
        transactionAmount: Double,
        correlationId: UUID
    ) {
        val transactionDto = TransactionDto(
            orderId = orderId,
            userId = userId,
            transactionAmount = transactionAmount
        )
        val payload = mapOf(
            "transactionDto" to transactionDto,
            "correlationId" to correlationId
        )
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Start transaction: $message")
        kafkaTemplate.send("start-transaction", correlationId.toString(), message)
    }
}