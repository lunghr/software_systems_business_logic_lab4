package com.example.software_systems_business_logic_lab1.payment.bank.controllers

import com.example.software_systems_business_logic_lab1.payment.bank.services.BankService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bank")
@Tag(name = "Bank Controller UTILITY CONTROLLER", description = "Controller for managing bank-related operations")
class BankController(
    private val bankService: BankService
) {
    @Operation(
        summary = "Create new bank account",
        description = "Creates a new bank account with the specified details"
    )
    @PostMapping("/create/bank-account")
    fun createBankAccount() = bankService.createBankAccount()


    @Operation(summary = "Create new card", description = "Creates a new card with the specified details")
    @PostMapping("/{accountNumber}/create/card")
    fun createCard(
        @Parameter(
            description = "Account number of the bank account to which the card will be linked",
            example = "1234567890"
        )
        @PathVariable accountNumber: String,
    ) = bankService.createCard(accountNumber)

    @Operation(
        summary = "Method to validate card data",
        description = "Validates the card data provided by the user from the client"
    )
    // i know it's horrible, but i just don't wanna test it in swagger in any other way
    @PostMapping("/validate/{cardNumber}/{cvv}")
    fun validateCardData(
        @Parameter(
            description = "Card number to be validated",
            example = "1234567890123456"
        )
        @PathVariable cardNumber: String,
        @Parameter(
            description = "Expiration date of the card",
            example = "12/25"
        )
        @RequestBody expirationDate: String,
        @Parameter(
            description = "CVV code of the card",
            example = "123"
        )
        @PathVariable cvv: String
    ) = bankService.validateCard(cardNumber, expirationDate, cvv)

    @Operation(
        summary = "Top up account balance",
        description = "Tops up the balance of the specified bank account by card number"
    )
    @PostMapping("/top-up/{cardNumber}/{amount}")
    fun topUpBalance(
        @Parameter(
            description = "Card number used for topping up the account",
            example = "885351475761580881"
        )
        @PathVariable cardNumber: String,
        @Parameter(
            description = "Amount to top up the account",
            example = "100.0"
        )
        @PathVariable amount: Double
    ) = bankService.topUpBankAccount(cardNumber, amount)


    @Operation(
        summary = "Process a payment transaction",
        description = "Processes a payment transaction using the provided details"
    )
    @PostMapping("/process-transaction")
    fun processTransaction(
        @Parameter(
            description = "Amount of the transaction to be processed",
            example = "100.0"
        )
        @RequestParam transactionAmount: String,
        @Parameter(
            description = "Card data for the transaction",
            example = "{\"id\": \"f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd\", \"cardNumber\": \"1234567890123456\", \"cvv\": \"123\", \"expirationDate\": \"12/25\" }"
        )
        @RequestBody cardData: OzonPaymentData
    ) = bankService.processTransaction(cardData, transactionAmount.toDouble())


}