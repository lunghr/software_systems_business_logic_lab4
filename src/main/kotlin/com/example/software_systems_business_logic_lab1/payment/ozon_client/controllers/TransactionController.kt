package com.example.software_systems_business_logic_lab1.payment.ozon_client.controllers

import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val transactionRepository: PaymentTransactionRepository
) {

    @Operation(
        summary = "Process a payment transaction",
        description = "Processes a payment transaction using the provided details"
    )
    @RequestMapping("/process/{orderId}/{paymentMethodId}")
    fun processTransaction(
        @Parameter(
            description = "ID of the order for which the transaction is being processed",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable orderId: UUID,
        @Parameter(
            description = "ID of the payment method being used for the transaction",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable paymentMethodId: UUID
    ) {

        // Implement transaction processing logic here
    }
}