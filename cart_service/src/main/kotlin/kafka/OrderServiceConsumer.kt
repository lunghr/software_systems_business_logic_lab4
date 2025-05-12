package com.example.kafka

import com.example.service.CartService
import com.fasterxml.jackson.databind.ObjectMapper
import model.ItemDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderServiceConsumer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val cartService: CartService
) {
    @KafkaListener(topics = ["get-cart"])
    fun sendCart(message: String) {
        val data = ObjectMapper().readTree(message)
        val userId = UUID.fromString(data.get("userId").asText())
        val correlationId = UUID.fromString(data.get("correlationId").asText())
        val cartItems = cartService.getListOfCartProductsForOrder(userId).map { cartItem ->
            ItemDto(
                productId = cartItem.productId,
                quantity = cartItem.quantity,
                price = cartItem.price
            )
        }
        val payload = mapOf(
            "items" to cartItems,
            "correlationId" to correlationId.toString()
        )
        val responseMessage = ObjectMapper().writeValueAsString(payload)
        println("✅ Response message: $responseMessage")
        kafkaTemplate.send("get-cart-response", correlationId.toString(), responseMessage)
    }


    @KafkaListener(topics = ["book-order"])
    fun bookOrderItems(message: String){
        val data = ObjectMapper().readTree(message)
        val userId = UUID.fromString(data.get("userId").asText())
        cartService.bookOrder(userId)
    }
}