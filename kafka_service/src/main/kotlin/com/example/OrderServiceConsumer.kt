package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class OrderServiceConsumer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
    private val zookeeperReader: ZookeeperReader
) {

    @KafkaListener(topics = ["get-api-key"])
    fun sendApiKey(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()

        val apiKey = zookeeperReader.read("/mail/api_key")
        val payload = mapOf(
            "apiKey" to apiKey,
            "correlationId" to correlationId
        )

        val responseMessage = objectMapper.writeValueAsString(payload)
        println("✅ Sending API key response: $responseMessage")
        kafkaTemplate.send("get-api-key-response", correlationId, responseMessage)
    }


    @KafkaListener(topics = ["get-paid-template"])
    fun sendPaidTemplate(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()

        val paidTemplate = zookeeperReader.read("/mail/templates/paid_order")
        val payload = mapOf(
            "paidTemplate" to paidTemplate,
            "correlationId" to correlationId
        )

        val responseMessage = objectMapper.writeValueAsString(payload)
        println("✅ Sending paid template response: $responseMessage")
        kafkaTemplate.send("get-paid-template-response", correlationId, responseMessage)
    }


    @KafkaListener(topics = ["get-create-template"])
    fun sendCreateTemplate(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()

        val createTemplate = zookeeperReader.read("/mail/templates/created_order")
        val payload = mapOf(
            "createTemplate" to createTemplate,
            "correlationId" to correlationId
        )

        val responseMessage = objectMapper.writeValueAsString(payload)
        println("✅ Sending create template response: $responseMessage")
        kafkaTemplate.send("get-create-template-response", correlationId, responseMessage)
    }


    @KafkaListener(topics = ["get-cancelled-template"])
    fun sendCancelledTemplate(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()

        val cancelledTemplate = zookeeperReader.read("/mail/templates/cancelled_order")
        val payload = mapOf(
            "cancelledTemplate" to cancelledTemplate,
            "correlationId" to correlationId
        )

        val responseMessage = objectMapper.writeValueAsString(payload)
        println("✅ Sending cancelled template response: $responseMessage")
        kafkaTemplate.send("get-cancelled-template-response", correlationId, responseMessage)
    }
}