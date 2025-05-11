package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = ["com.example", "service", "config", "kafka"])
@EnableJpaRepositories(basePackages = ["com.example.repos"])
@EnableWebSecurity
class CartServiceApplication

fun main(args: Array<String>) {
    runApplication<CartServiceApplication>(*args)
}