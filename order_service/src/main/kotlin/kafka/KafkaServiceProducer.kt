package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class KafkaServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun getApiKey(correlationId: UUID) {
        val payload = mapOf("correlationId" to correlationId.toString())
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Sending get-api-key request")
        kafkaTemplate.send("get-api-key", correlationId.toString(), message)
    }


    fun getPaidTemplate(correlationId: UUID) {
        val payload = mapOf("correlationId" to correlationId.toString())
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Sending get-paid-template request")
        kafkaTemplate.send("get-paid-template", correlationId.toString(), message)
    }


    fun getCreateTemplate(correlationId: UUID) {
        val payload = mapOf("correlationId" to correlationId.toString())
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Sending get-create-template request")
        kafkaTemplate.send("get-create-template", correlationId.toString(), message)
    }


    fun getCancelledTemplate(correlationId: UUID) {
        val payload = mapOf("correlationId" to correlationId.toString())
        val message = objectMapper.writeValueAsString(payload)
        println("✅ Sending get-cancelled-template request")
        kafkaTemplate.send("get-cancelled-template", correlationId.toString(), message)
    }
}