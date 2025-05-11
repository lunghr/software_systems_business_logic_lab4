package com.example.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.UUID

@Component
class ProductServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun sendProductAvailability(productId: UUID, quantity: Int, correlationId: UUID) {
        val payload = mapOf(
            "productId" to productId.toString(),
            "quantity" to quantity.toString(),
            "correlationId" to correlationId.toString()
        )
        val message = ObjectMapper().writeValueAsString(payload)
        kafkaTemplate.send("product-availability",correlationId.toString(), message)
    }
}