package com.example.kafka.tmp

data class ValidationBankResponse(
    val isValid: Boolean,
)

data class PaymentTransactionResponse(
    val isSuccess: Boolean,
)
