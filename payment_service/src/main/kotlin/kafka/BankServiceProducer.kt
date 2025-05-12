package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BankServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun sendCardDataValidationRequest(cvv: String, expiryDate: String, cardNumber: String, correlationId: UUID) {
        val payload = mapOf(
            "cvv" to cvv,
            "expiryDate" to expiryDate,
            "cardNumber" to cardNumber,
            "correlationId" to correlationId.toString()
        )

        val message = objectMapper.writeValueAsString(payload)
        println("✅ Sending card validation request: $message")
        kafkaTemplate.send("validate-card", correlationId.toString(), message)
    }

    fun startPaymentTransaction(cardNumber: String, transactionAmount: Double, correlationId: UUID) {
        val payload = mapOf(
            "cardNumber" to cardNumber,
            "transactionAmount" to transactionAmount,
            "correlationId" to correlationId.toString()
        )
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Start payment transaction request: $message")
        kafkaTemplate.send("start-payment-transaction", correlationId.toString(), message)
    }


}