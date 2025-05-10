package com.example.service

import com.example.model.Product
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service


@Service
class ProductEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, Product>
) {
    fun send(product: Product) {
        kafkaTemplate.send("product-events", product)
        println("📤 Отправлено в Kafka: ${product.name}")
    }

//    fun send(product: Product) {
//        val message = MessageBuilder
//            .withPayload(product)
//            .setHeader(KafkaHeaders.TOPIC, "product-events")
//            .setHeader("__TypeId__", Product::class.java.name) // 👈 это ключ
//            .build()
//
//        kafkaTemplate.send(message)
//        println("📤 Отправлено с header __TypeId__: ${product.name}")
//    }
}