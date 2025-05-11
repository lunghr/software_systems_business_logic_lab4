package com.example.service.kafka

import com.example.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class CartServiceListener(
    private val productService: ProductService,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    @KafkaListener(topics = ["product-availability"])
    fun handleProductAvailability(message: String) {
        val data = ObjectMapper().readTree(message)
        val productId = UUID.fromString(data.get("productId").asText())
        val quantity = data.get("quantity").asInt()
        val correlationId = UUID.fromString(data.get("correlationId").asText())

        val availability = productService.isAvailableToOrder(productId, quantity)
        val response = mapOf(
            "exists" to availability.first,
            "enough" to availability.second,
            "correlationId" to correlationId.toString()
        )
        val responseMessage = ObjectMapper().writeValueAsString(response)
        kafkaTemplate.send("product-availability-response", correlationId.toString(), responseMessage)
    }
}