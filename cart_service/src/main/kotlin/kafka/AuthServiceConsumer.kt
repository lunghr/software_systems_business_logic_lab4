package com.example.kafka

import com.example.service.CartService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class AuthServiceConsumer(
    private val cartService: CartService,
    ) {
    private val logger: Logger = LoggerFactory.getLogger(AuthServiceConsumer::class.java)

    @KafkaListener(topics = ["user-creation-notification"])
    fun handleUserCreationNotification(message: String) {
        val data = ObjectMapper().readTree(message)
        val userId = UUID.fromString(data.get("userId").asText())
        cartService.createCart(userId)
        logger.info("Cart created for user: $userId")
    }


}