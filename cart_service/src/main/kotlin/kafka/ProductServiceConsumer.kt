package com.example.kafka

import com.example.kafka.tmp.ProductAvailabilityResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductServiceConsumer(
    private val responseStorage: ResponseStorage
) {

    @KafkaListener(topics=["product-availability-response"])
    fun handleProductAvailabilityResponse(message: String) {
        val data = ObjectMapper().readTree(message)
        val productAvailabilityResponse = ProductAvailabilityResponse(
            exists = data.get("exists").asBoolean(),
            enough = data.get("enough").asBoolean(),
            price = data.get("price").asDouble(0.0)
        )
        val correlationId = data.get("correlationId").asText()
        responseStorage.storeResponse(correlationId, productAvailabilityResponse)
    }
}