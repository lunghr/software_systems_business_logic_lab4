package com.example.controller

import com.example.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = ["*"])
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping("/create")
    fun createOrder(
        @RequestHeader("Authorization") token: String,
    ) = orderService.createOrder(token)

}