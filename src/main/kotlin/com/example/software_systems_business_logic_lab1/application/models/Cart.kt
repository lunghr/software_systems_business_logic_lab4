package com.example.software_systems_business_logic_lab1.application.models


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.util.UUID


@Entity
@Table(name="carts")
data class Cart(
    @Id
    @Column(name = "cart_id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "total_price")
    val totalPrice: Double = 0.0,
)