package com.example.software_systems_business_logic_lab1.payment.ozon_client.repos

import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PaymentMethodRepository: JpaRepository<PaymentMethod, UUID> {
    fun findAllByUserId(userId: UUID): List<PaymentMethod>
    fun findPaymentMethodById(id: UUID): PaymentMethod?
}