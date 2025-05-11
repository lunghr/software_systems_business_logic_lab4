package com.example.model

import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import java.util.UUID


@Entity
@Table(name = "carts")
data class Cart(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id")
    val userId: UUID,

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val items: List<CartItem> = emptyList()
)