package com.example.software_systems_business_logic_lab1.payment.ozon_client.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID


@Entity
@Table(name = "ozon_payment_data")
data class OzonPaymentData(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(name = "card_number")
    val cardNumber: String,
    @Column(name = "expiration_date")
    val expirationDate: String,
    @Column(name = "cvv")
    val cvv: String,
)