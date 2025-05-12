package com.example.kafka

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import model.TransactionDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentServiceConsumer(
    private val objectMapper: ObjectMapper,
    private val responseStorage: ResponseStorage
) {
    @KafkaListener(topics = ["close-transaction"])
    fun handleTransaction(message: String) {
        val data = objectMapper.readTree(message)
        println("✅ Transaction result: $message")
        val correlationId = data.get("correlationId").asText()
        val transactionDtoResponse = data.get("transactionDtoResponse")
        val typeRef = object : TypeReference<TransactionDto>() {}
        val transactionDtoObj = objectMapper.readValue(transactionDtoResponse.toString(), typeRef)
        responseStorage.storeResponse(correlationId, transactionDtoObj)
    }
}