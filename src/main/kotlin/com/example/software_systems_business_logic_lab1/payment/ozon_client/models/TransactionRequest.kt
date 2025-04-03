package com.example.software_systems_business_logic_lab1.payment.ozon_client.models

data class TransactionRequest(
    val transactionAmount: Double,
    val cardData: OzonPaymentData
)