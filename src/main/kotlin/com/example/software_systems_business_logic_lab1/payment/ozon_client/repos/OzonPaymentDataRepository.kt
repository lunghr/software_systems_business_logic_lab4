package com.example.software_systems_business_logic_lab1.payment.ozon_client.repos

import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OzonPaymentDataRepository:JpaRepository<OzonPaymentData, UUID> {
    fun findByCardNumber(cardNumber: String): OzonPaymentData?

}