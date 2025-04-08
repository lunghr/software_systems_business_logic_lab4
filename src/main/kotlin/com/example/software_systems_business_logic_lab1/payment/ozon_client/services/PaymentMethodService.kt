package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.repos.UserRepository
import com.example.software_systems_business_logic_lab1.payment.bank.models.enums.PaymentType
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.OzonPaymentDataRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentMethodRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.UUID


@Service
class PaymentMethodService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val paymentTransactionRepository: PaymentTransactionRepository,
    private val ozonPaymentDataRepository: OzonPaymentDataRepository,
    private val userRepository: UserRepository,
    private val restTemplate: RestTemplate
) {

    fun getPaymentMethodsByUserId(userId: UUID): List<PaymentMethod> {
        println(userId)
        return paymentMethodRepository.findAllByUserId(userId)
    }

    @Transactional
    fun addNewPaymentMethod(cardNumber: String, cvv: String, expiryDate: String, userId: UUID): PaymentMethod {
        val user = userRepository.findUserById(userId)
            ?: throw IllegalArgumentException("User not found")

        val bankValidationUrl = "http://localhost:8080/bank/validate/$cardNumber/$cvv"
        val isValid = restTemplate.postForObject(bankValidationUrl, expiryDate, Boolean::class.java)
            ?: throw RuntimeException("Failed to validate card")

        if (!isValid) {
            throw IllegalArgumentException("Card data is invalid")
        }
        val savedOzonPaymentData = ozonPaymentDataRepository.save(toOzonPaymentData(cardNumber, expiryDate, cvv))
        return paymentMethodRepository.save(toPaymentMethod(user, savedOzonPaymentData, PaymentType.CARD))

    }

    fun getPaymentMethodById(paymentMethodId: UUID): PaymentMethod? {
        return paymentMethodRepository.findById(paymentMethodId).orElse(null)
    }

}
