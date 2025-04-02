package com.example.software_systems_business_logic_lab1.payment.ozon_client.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Method Controller", description = "Controller for managing payment methods")
class PaymentMethodController {

    @Operation(summary = "Get all available user payment methods")
    @GetMapping("/get/all/{userId}")
    fun getAllPaymentMethods(
        @Parameter(description = "ID of the user whose payment methods are to be retrieved")
        @PathVariable userId: UUID
    ) {
        // Logic to retrieve all payment methods
    }


}