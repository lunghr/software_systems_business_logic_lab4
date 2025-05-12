package com.example.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cart_items")
data class CartItem(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "cart_id")
    val cart: Cart,

    @Column(name = "product_id")
    val productId: UUID,

    @Column(name = "product_name")
    val quantity: Int,

    @Column(name = "price")
    val price: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    var availability: Availability = Availability.AVAILABLE
)