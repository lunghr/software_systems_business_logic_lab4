package com.example.kafka


import com.example.kafka.tmp.PaymentTransactionResponse
import com.example.kafka.tmp.ValidationBankResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class BankServiceConsumer(
    private val objectMapper: ObjectMapper,
    private val responseStorage: ResponseStorage
) {

    @KafkaListener(topics = ["validate-card-response"])
    fun handleValidateCardResponse(message: String){
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val isValid = data.get("isValid").asBoolean()
        println("✅ Get card validation: $message")
        responseStorage.storeResponse(correlationId, ValidationBankResponse(isValid))
    }

    @KafkaListener(topics = ["close-payment-transaction"])
    fun handleTransactionResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val isSuccess = data.get("isSuccess").asBoolean()
        println("✅ Payment Transaction result: $message")

        responseStorage.storeResponse(correlationId, PaymentTransactionResponse(isSuccess))
    }
}