package com.example.software_systems_business_logic_lab1.payment.ozon_client.services

import com.example.software_systems_business_logic_lab1.application.models.InvalidCardDataException
import com.example.software_systems_business_logic_lab1.application.models.PaymentMethodAlreadyExistsException
import com.example.software_systems_business_logic_lab1.application.models.UserNotFoundException
import com.example.software_systems_business_logic_lab1.application.repos.UserRepository
import com.example.software_systems_business_logic_lab1.payment.bank.models.enums.PaymentType
import com.example.software_systems_business_logic_lab1.payment.bank.services.BankService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.OzonPaymentDataRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentMethodRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class PaymentMethodService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val ozonPaymentDataRepository: OzonPaymentDataRepository,
    private val userRepository: UserRepository,
    private val bankService: BankService
) {

    fun getPaymentMethodsByUserId(userId: UUID): List<PaymentMethod> {
        return paymentMethodRepository.findAllByUserId(userId)
    }

    @Transactional
    fun addNewPaymentMethod(cardNumber: String, cvv: String, expiryDate: String, userId: UUID): PaymentMethod {
        val user = userRepository.findUserById(userId)
            ?: throw UserNotFoundException()

        val isValid = bankService.validateCard(cardNumber, expiryDate, cvv)

        if (!isValid) {
            throw InvalidCardDataException()
        }
        val paymentData = toOzonPaymentData(cardNumber, cvv, expiryDate)
        if (isExist(paymentData)) throw PaymentMethodAlreadyExistsException()
        val savedOzonPaymentData = ozonPaymentDataRepository.save(toOzonPaymentData(cardNumber, expiryDate, cvv))
        return paymentMethodRepository.save(toPaymentMethod(user, savedOzonPaymentData, PaymentType.CARD))

    }

    fun getPaymentMethodById(paymentMethodId: UUID): PaymentMethod? {
        return paymentMethodRepository.findById(paymentMethodId).orElse(null)
    }

    private fun isExist(paymentData: OzonPaymentData): Boolean {
        return ozonPaymentDataRepository.findByCardNumber(paymentData.cardNumber) != null
    }

}
