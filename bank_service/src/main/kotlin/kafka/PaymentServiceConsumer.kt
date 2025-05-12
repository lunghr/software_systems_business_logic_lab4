package com.example.kafka

import com.example.service.BankService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentServiceConsumer(
    private val bankService: BankService,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @KafkaListener(topics = ["validate-card"])
    fun handleValidateCardRequest(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val cvv = data.get("cvv").asText()
        val expiryDate = data.get("expiryDate").asText()
        val cardNumber = data.get("cardNumber").asText()
        println("✅ Get card validation: $message")
        val isValid = bankService.validateCard(cardNumber, expiryDate, cvv)

        val response = mapOf(
            "correlationId" to correlationId,
            "isValid" to isValid
        )
        val responseMessage = objectMapper.writeValueAsString(response)
        println("✅ Validation response: $responseMessage")
        kafkaTemplate.send("validate-card-response", correlationId, responseMessage)
    }

    @KafkaListener(topics = ["start-payment-transaction"])
    fun processTransactionRequest(message: String) {
        val data = objectMapper.readTree(message)
        println("✅ Get transaction request: $message")
        val correlationId = data.get("correlationId").asText()
        val cardNumber = data.get("cardNumber").asText()
        val transactionAmount = data.get("transactionAmount").asDouble()

        val isSuccess = bankService.processTransaction(cardNumber, transactionAmount)

        val response = mapOf(
            "correlationId" to correlationId,
            "isSuccess" to isSuccess
        )
        val responseMessage = objectMapper.writeValueAsString(response)
        println("✅ Transaction response: $responseMessage")
        kafkaTemplate.send("close-payment-transaction", correlationId, responseMessage)
    }
}