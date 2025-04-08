package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.OrderService

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/order")
class OrderController(
     private val orderService: OrderService
) {
    @PostMapping("/create/{cartId}")
    fun createOrder(
        @PathVariable cartId: UUID,
        @RequestBody products: List<UUID>
    ) = orderService.createOrder(cartId, products)

}