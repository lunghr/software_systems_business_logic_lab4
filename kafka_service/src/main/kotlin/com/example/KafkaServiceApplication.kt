package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class KafkaServiceApplication

fun main(args: Array<String>) {
    println("🔧 Starting Spring...")
    runApplication<KafkaServiceApplication>(*args)

}