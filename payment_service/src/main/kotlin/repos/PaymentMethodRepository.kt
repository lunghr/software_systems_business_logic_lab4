package com.example.repos

import com.example.model.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PaymentMethodRepository: JpaRepository<PaymentMethod, UUID> {
    fun findAllByUserId(userId: UUID): List<PaymentMethod>
    fun findByCardNumber(cardNumber: String): PaymentMethod?
    fun findPaymentMethodById(id: UUID): PaymentMethod?
    fun findFirstByUserId(userId: UUID): PaymentMethod?
}