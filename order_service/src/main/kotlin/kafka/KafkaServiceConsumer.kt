package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaServiceConsumer(
    private val responseStorage: ResponseStorage,
    private val objectMapper: ObjectMapper,
) {

    @KafkaListener(topics = ["get-api-key-response"])
    fun handleApiKeyResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val apiKey = data.get("apiKey").asText()
        responseStorage.storeResponse(correlationId, apiKey)
    }

    @KafkaListener(topics = ["get-paid-template-response"])
    fun handlePaidTemplateResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val paidTemplate = data.get("paidTemplate").asText()
        responseStorage.storeResponse(correlationId, paidTemplate)
    }


    @KafkaListener(topics = ["get-create-template-response"])
    fun handleCreateTemplateResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val createTemplate = data.get("createTemplate").asText()
        responseStorage.storeResponse(correlationId, createTemplate)
    }

    @KafkaListener(topics = ["get-cancelled-template-response"])
    fun handleCancelledTemplateResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val cancelledTemplate = data.get("cancelledTemplate").asText()
        responseStorage.storeResponse(correlationId, cancelledTemplate)
    }
}