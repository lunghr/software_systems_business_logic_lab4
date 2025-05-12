package com.example.service

import com.example.kafka.BankServiceProducer
import com.example.kafka.ResponseStorage
import com.example.kafka.tmp.ValidationBankResponse
import com.example.model.PaymentMethod
import com.example.repos.PaymentMethodRepository
import model.BankServiceException
import model.InvalidCardDetailsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import service.JwtService
import java.util.UUID


@Service
class PaymentMethodService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val jwtService: JwtService,
    private val bankServiceProducer: BankServiceProducer,
    private val responseStorage: ResponseStorage
) {

    fun getPaymentMethods(token: String): List<UUID> {
        return paymentMethodRepository.findAllByUserId(getUserId(token))
            .map { it.id }
    }

    private fun isExist(cardNumber: String): Boolean {
        return paymentMethodRepository.findByCardNumber(cardNumber) != null
    }

    fun getUserId(token: String): UUID = jwtService.extractId(jwtService.extractToken(token))


    @Transactional
    fun addNewPaymentMethod(token: String, cardNumber: String, cvv: String, expiryDateOld: String): ResponseEntity<Any> {
        val expiryDate = expiryDateOld.replace("-", "/")
        val userId = getUserId(token)
        if (isExist(cardNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Card already added to user account")
        }
        val correlationId = UUID.randomUUID()
        bankServiceProducer.sendCardDataValidationRequest(
            cardNumber = cardNumber,
            cvv = cvv,
            expiryDate = expiryDate,
            correlationId = correlationId
        )
        val response = responseStorage.waitForResponse(correlationId.toString(), 5, ValidationBankResponse::class.java)
        response?.let {
            if (it.isValid) {
                paymentMethodRepository.save(
                    PaymentMethod(
                        userId = userId,
                        cardNumber = cardNumber
                    )
                )
                return ResponseEntity(HttpStatus.OK)
            } else {
                throw InvalidCardDetailsException()
            }
        } ?: throw BankServiceException()
    }

    fun isValidPaymentMethod(id: UUID): Boolean {
        return paymentMethodRepository.findPaymentMethodById(id) != null
    }

    fun getPaymentMethodById(id: UUID): PaymentMethod? {
        return paymentMethodRepository.findPaymentMethodById(id)
    }

    fun getFirstPaymentMethodByUserId(userId: UUID): PaymentMethod? {
        return paymentMethodRepository.findFirstByUserId(userId)
    }
}
