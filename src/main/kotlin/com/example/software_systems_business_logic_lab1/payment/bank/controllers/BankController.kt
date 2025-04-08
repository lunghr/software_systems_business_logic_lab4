package com.example.software_systems_business_logic_lab1.payment.bank.controllers

import com.example.software_systems_business_logic_lab1.payment.bank.services.BankService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bank")
class BankController(
    private val bankService: BankService
) {
    @PostMapping("/create/bank-account")
    fun createBankAccount() = bankService.createBankAccount()

    @PostMapping("/{accountNumber}/create/card")
    fun createCard(
        @PathVariable accountNumber: String,
    ) = bankService.createCard(accountNumber)

    // i know it's horrible, but i just don't wanna test it in swagger in any other way
    @PostMapping("/validate/{cardNumber}/{cvv}")
    fun validateCardData(
        @PathVariable cardNumber: String,
        @RequestBody expirationDate: String,
        @PathVariable cvv: String
    ) = bankService.validateCard(cardNumber, expirationDate, cvv)

    @PostMapping("/top-up/{cardNumber}/{amount}")
    fun topUpBalance(
        @PathVariable cardNumber: String,
        @PathVariable amount: Double
    ) = bankService.topUpBankAccount(cardNumber, amount)

    @PostMapping("/process-transaction/{transactionAmount}")
    fun processTransaction(
        @PathVariable transactionAmount: Double,
        @RequestBody cardData: OzonPaymentData
    ) = bankService.processTransaction(cardData, transactionAmount)


}