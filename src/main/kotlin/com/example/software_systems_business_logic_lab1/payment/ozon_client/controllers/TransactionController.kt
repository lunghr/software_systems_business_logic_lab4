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

    @RequestMapping("/process/{orderId}/{paymentMethodId}")
    fun processTransaction(
        @PathVariable orderId: UUID,
        @PathVariable paymentMethodId: UUID
    ) {

        // Implement transaction processing logic here
    }
}