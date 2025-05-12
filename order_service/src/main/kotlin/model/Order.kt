package com.example.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "orders")
data class Order(
    @Id val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id")
    val userId: UUID,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status")
    val status: OrderStatus = OrderStatus.PENDING,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var items: List<OrderItem> = emptyList(),

    @Column(name = "total_price")
    var totalPrice: Double = 0.0
)