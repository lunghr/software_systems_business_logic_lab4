package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = ["com.example", "service", "config"])
@EnableCassandraRepositories(basePackages = ["com.example.repos"])
class ProductServiceApplication

fun main(args: Array<String>) {
    runApplication<ProductServiceApplication>(*args)
}