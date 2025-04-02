package com.example.software_systems_business_logic_lab1.payment.ozon_client.models

import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.payment.bank.enums.PaymentType
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "payment_methods")
data class PaymentMethod (
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    val user: User,
    @JoinColumn(name = "ozon_payment_data_id")
    @OneToOne
    val ozonPaymentData: OzonPaymentData,
    @Column(name = "payment_type")
    val paymentType: PaymentType = PaymentType.CARD
)