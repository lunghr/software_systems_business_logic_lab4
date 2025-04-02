package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.repos.UserRepository
import com.example.software_systems_business_logic_lab1.payment.bank.enums.PaymentType
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.OzonPaymentDataRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentMethodRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.UUID


@Service
class PaymentMethodService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val paymentTransactionRepository: PaymentTransactionRepository,
    private val ozonPaymentDataRepository: OzonPaymentDataRepository,
    private val userRepository: UserRepository
    private val restTemplate: RestTemplate
) {

    fun getPaymentMethodsByUserId(userId: UUID): List<PaymentMethod> {
        return paymentMethodRepository.findAll().filter { it.user.id == userId }
    }

    fun addNewPaymentMethod(data: Map<String, String>, userId: UUID): PaymentMethod {
        val user = userRepository.findUserById(userId)
            ?: throw IllegalArgumentException("User not found")
        val cardDetails = data["details"] as? Map<*, *>
            ?: throw IllegalArgumentException("Card details are required")
        val cardNumber = cardDetails["cardNumber"] ?: throw IllegalArgumentException("Card number is required")
        val expiryDate = cardDetails["expiryDate"] ?: throw IllegalArgumentException("Expiry date is required")
        val cvv = cardDetails["cvv"] ?: throw IllegalArgumentException("CVV is required")

        val bankValidationUrl = "http://localhost:8080/bank/validate/$cardNumber/$cvv"
        val isValid = restTemplate.postForObject(bankValidationUrl, expiryDate, Boolean::class.java)
            ?: throw RuntimeException("Failed to validate card")

        if (!isValid) {
            throw IllegalArgumentException("Card validation failed")
        }

//        val ozonPaymentData = OzonPaymentData(
//            cardNumber = cardNumber,
//            expirationDate = expiryDate,
//            cvv = cvv
//        )
//
//        val savedOzonPaymentData = ozonPaymentDataRepository.save(ozonPaymentData)
//        val paymentMethod = PaymentMethod(
//            user = user,
//            ozonPaymentData = savedOzonPaymentData,
//            paymentType = PaymentType.CARD
//        )

    }

}
}