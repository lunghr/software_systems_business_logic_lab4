package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class CartServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun getCart(userId: UUID, correlationId: UUID) {
        val payload = mapOf(
            "userId" to userId.toString(),
            "correlationId" to correlationId.toString()
        )
        val message = objectMapper.writeValueAsString(payload)
        kafkaTemplate.send("get-cart", correlationId.toString(), message)
    }

    fun bookOrder(userId: UUID) {
        val payload = mapOf(
            "userId" to userId.toString()
        )
        val message = objectMapper.writeValueAsString(payload)
        kafkaTemplate.send("book-order", message)
    }
}