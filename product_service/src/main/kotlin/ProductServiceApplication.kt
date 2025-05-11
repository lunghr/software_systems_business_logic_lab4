package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = ["com.example", "service", "config"])
@EnableCassandraRepositories(basePackages = ["com.example.repos"])
@EnableWebSecurity
class ProductServiceApplication

fun main(args: Array<String>) {
    runApplication<ProductServiceApplication>(*args)
}