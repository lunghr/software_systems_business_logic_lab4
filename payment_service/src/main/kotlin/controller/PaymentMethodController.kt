package com.example.controller

import com.example.service.PaymentMethodService
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = ["*"])
class PaymentMethodController(
    private val paymentMethodService: PaymentMethodService
) {


    @GetMapping("/get")
    fun getAllPaymentMethods(
        @RequestHeader("Authorization") token: String,
    ) = paymentMethodService.getPaymentMethods(token)

    @PostMapping("/add")
    fun addPaymentMethod(
        @RequestHeader("Authorization") token: String,
        @RequestParam cardNumber: String,
        @RequestParam expirationDate: String,
        @RequestParam cvv: String
    ) = paymentMethodService.addNewPaymentMethod(
        cardNumber = cardNumber,
        cvv = cvv,
        expiryDateOld = expirationDate,
        token = token
    )

}