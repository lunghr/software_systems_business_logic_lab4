package com.example.kafka

import com.example.kafka.tmp.ItemsResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.example.model.OrderItem
import com.fasterxml.jackson.databind.ObjectMapper
import model.ItemDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CartServiceConsumer(
    private val responseStorage: ResponseStorage,
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = ["get-cart-response"])
    fun handleGetCartResponse(message: String) {
        val data = objectMapper.readTree(message)
        val correlationId = data.get("correlationId").asText()
        val cartItems = data.get("items")

        val typeRef = object : TypeReference<List<ItemDto>>() {}
        if (cartItems.isNull) {
            responseStorage.storeResponse(correlationId, ItemsResponse(emptyList()))
            return
        } else {
            val items: List<ItemDto> = objectMapper.readValue(cartItems.toString(), typeRef)
            responseStorage.storeResponse(correlationId, ItemsResponse(items))
        }
    }

}
