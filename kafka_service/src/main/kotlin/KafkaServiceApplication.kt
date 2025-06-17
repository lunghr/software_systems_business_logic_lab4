package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
//@ComponentScan(basePackages = ["com.example", "service", "config", "kafka"])
class KafkaServiceApplication

fun main(args: Array<String>) {
    runApplication<KafkaServiceApplication>(*args)
}