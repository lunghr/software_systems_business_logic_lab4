package com.example.controller

import com.example.service.BankService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = ["*"])
class BankController(
    private val bankService: BankService
) {

    @PostMapping("/create/card")
    fun createCard() = bankService.createCard()

    @PostMapping("/balance/{cardNumber}")
    fun topUpBalance(
        @PathVariable cardNumber: String,
        @RequestParam amount: Double
    ) = bankService.topUpBalance(cardNumber, amount)
}