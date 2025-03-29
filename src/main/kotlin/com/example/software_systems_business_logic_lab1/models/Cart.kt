package com.example.software_systems_business_logic_lab1.models

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID


@Table ("cart")
data class Cart(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val totalPrice: Double = 0.0,
)