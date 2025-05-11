package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CartServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    fun sendUserCreationNotification(userId: UUID){
        val message = mapOf(
            "userId" to userId.toString()
        )
        val messageString = ObjectMapper().writeValueAsString(message)
        kafkaTemplate.send("user-creation-notification", messageString)

    }

}