package com.example.software_systems_business_logic_lab1.payment.ozon_client.controllers

import com.example.software_systems_business_logic_lab1.payment.bank.models.enums.PaymentType
import com.example.software_systems_business_logic_lab1.payment.ozon_client.services.PaymentMethodService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Method Controller", description = "Controller for managing payment methods")
class PaymentMethodController(
    private val paymentMethodService: PaymentMethodService
) {

    @Operation(
        summary = "Get all available user payment methods",
        description = "Retrieves all payment methods for a specific user"
    )
    @GetMapping("/get/all/{userId}")
    fun getAllPaymentMethods(
        @PathVariable userId: UUID
    ) = paymentMethodService.getPaymentMethodsByUserId(userId)

    @PostMapping("/add/{userId}")
    fun addPaymentMethod(
        @PathVariable userId: UUID,
        @RequestParam paymentType: PaymentType,
        @RequestParam cardNumber: String,
        @RequestParam expirationDate: String,
        @RequestParam cvv: String
    ) = paymentMethodService.addNewPaymentMethod(
        cardNumber = cardNumber,
        cvv = cvv,
        expiryDate = expirationDate,
        userId = userId
    )

}