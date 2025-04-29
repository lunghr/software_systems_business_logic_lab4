package com.example.software_systems_business_logic_lab1.payment.ozon_client.controllers

import com.example.software_systems_business_logic_lab1.payment.ozon_client.services.TransactionService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = ["*"])
class TransactionController(
    private val transactionService: TransactionService
) {
    @PostMapping("/process/{orderId}/{paymentMethodId}")
    fun processTransaction(
        @PathVariable orderId: UUID,
        @PathVariable paymentMethodId: UUID
    ) = transactionService.processTransaction(
        paymentMethodId = paymentMethodId,
        orderId = orderId
    )
}