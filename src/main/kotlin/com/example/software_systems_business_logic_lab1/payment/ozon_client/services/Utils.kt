package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.models.Order
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.payment.bank.models.enums.PaymentType
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentTransaction
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus

fun toOzonPaymentData(cardNumber: String, cvv: String, expirationDate: String): OzonPaymentData {
    return OzonPaymentData(
        cardNumber = cardNumber,
        cvv = cvv,
        expirationDate = expirationDate
    )
}

fun toPaymentMethod(user: User, ozonPaymentData: OzonPaymentData, paymentType: PaymentType): PaymentMethod {
    return PaymentMethod(
        user = user,
        ozonPaymentData = ozonPaymentData,
        paymentType = paymentType
    )
}


fun toTransaction(
    paymentMethod: PaymentMethod,
    order: Order,
    amount: Double
): PaymentTransaction {
    return PaymentTransaction(
        paymentMethod = paymentMethod,
        order = order,
        transactionAmount = amount,
        transactionStatus = TransactionStatus.CREATED,
    )
}