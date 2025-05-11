package com.example.repos

import com.example.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface CartItemRepository : JpaRepository<CartItem, UUID> {
    fun findByCartIdAndProductId(cartId: UUID, productId: UUID): CartItem?

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :id")
    fun updateQuantity(id: UUID, quantity: Int): Int
}