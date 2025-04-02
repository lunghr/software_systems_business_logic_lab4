package com.example.software_systems_business_logic_lab1.payment.ozon_client.models

import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.payment.bank.enums.PaymentType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "payment_methods")
data class PaymentMethod (
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name="user_id")
    val user: User,
    @Column(name = "payment_type")
    val paymentType: PaymentType
)