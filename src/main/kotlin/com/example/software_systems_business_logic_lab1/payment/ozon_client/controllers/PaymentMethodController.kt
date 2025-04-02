package com.example.software_systems_business_logic_lab1.payment.ozon_client.controllers

import com.example.software_systems_business_logic_lab1.payment.ozon_client.services.PaymentMethodService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.UUID


@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Method Controller", description = "Controller for managing payment methods")
class PaymentMethodController(
    private val restTemplate: RestTemplate,
    private val paymentMethodService: PaymentMethodService
) {

    @Operation(
        summary = "Get all available user payment methods",
        description = "Retrieves all payment methods for a specific user"
    )
    @GetMapping("/get/all/{userId}")
    fun getAllPaymentMethods(
        @Parameter(
            description = "ID of the user whose payment methods are to be retrieved",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable userId: UUID
    ) = paymentMethodService.getPaymentMethodsByUserId(userId)

    @Operation(summary = "Add a new payment method")
    @PostMapping("/add/{userId}")
    fun addPaymentMethod(
        @Parameter(
            description = "ID of the user to whom the payment method will be added",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable userId: UUID,
        @Parameter(
            description = "Type of the payment method to be added",
            example = "CARD"
        )


    ) {
        // Logic to add a new payment method
    }


}