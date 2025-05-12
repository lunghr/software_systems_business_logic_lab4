package com.example.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "payment_methods")
data class PaymentMethod (
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(name = "user_id")
    val userId: UUID,
    val cardNumber: String
)