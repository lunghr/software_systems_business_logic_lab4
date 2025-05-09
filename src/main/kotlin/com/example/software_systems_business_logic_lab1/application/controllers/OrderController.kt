package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.OrderService
import org.springframework.web.bind.annotation.*

import java.util.UUID

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = ["*"])
class OrderController(
     private val orderService: OrderService
) {
//    @PostMapping("/create/{cartId}")
//    fun createOrder(
//        @PathVariable cartId: UUID,
//        @RequestBody products: List<UUID>
//    ) = orderService.createOrder(cartId, products)

}