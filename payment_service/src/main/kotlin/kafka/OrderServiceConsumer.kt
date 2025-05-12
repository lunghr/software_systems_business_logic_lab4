package com.example.kafka

import com.example.service.TransactionService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import model.TransactionDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderServiceConsumer(
    private val transactionService: TransactionService,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @KafkaListener(topics = ["start-transaction"])
    fun handleTransaction(message: String) {
        val data = objectMapper.readTree(message)
        println("✅ Get transaction request: $message")
        val correlationId = data.get("correlationId").asText()
        val transactionDto = data.get("transactionDto")
        val typeRef = object : TypeReference<TransactionDto>() {}
        val transactionDtoObj = objectMapper.readValue(transactionDto.toString(), typeRef)
        val response = transactionService.processTransaction(transactionDtoObj)
        val payload = mapOf(
            "transactionDtoResponse" to response,
            "correlationId" to correlationId
        )
        val responseMessage = objectMapper.writeValueAsString(payload)
        println("✅ Transaction response: $responseMessage")
        kafkaTemplate.send("close-transaction", correlationId, responseMessage)
    }
}