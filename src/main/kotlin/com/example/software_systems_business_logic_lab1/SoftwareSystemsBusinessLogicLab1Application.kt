package com.example.software_systems_business_logic_lab1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class SoftwareSystemsBusinessLogicLab1Application

fun main(args: Array<String>) {
    runApplication<SoftwareSystemsBusinessLogicLab1Application>(*args)
}
