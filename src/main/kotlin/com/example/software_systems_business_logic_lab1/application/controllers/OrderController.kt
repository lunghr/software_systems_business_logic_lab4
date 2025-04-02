package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/order")
@Tag(name = "Controller for operations with orders")
class OrderController(
     private val orderService: OrderService
) {
    @Operation(
        summary = "Create order",
        description = "Creates order by cart id with chosen products from cart"
    )
    @PostMapping("/create/{cartId}")
    fun createOrder(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID,
        @Parameter(
            description = "List of product ids",
            example = "[e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f, 9c1e8a0e-3b4f-4f5e-8b1c-6d5f8a0e3b4f]"
        )
        @RequestBody products: List<UUID>
    ) = orderService.createOrder(cartId, products)

}