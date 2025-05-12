package com.example.model

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id val id: UUID = UUID.randomUUID(),

    @Column(name = "product_id")
    val productId: UUID,

    @Column(name = "quantity")
    val quantity: Int,

    @Column(name = "price")
    val price: Double,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order
)