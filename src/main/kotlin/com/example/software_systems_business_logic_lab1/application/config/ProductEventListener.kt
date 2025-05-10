package com.example.software_systems_business_logic_lab1.application.config

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductEventListener {
    @KafkaListener(topics = ["product-events"], groupId = "product-group")
    fun listen(product: Product) {
        println("🔥 Локально получено сообщение: ${product.name}")
    }
}
