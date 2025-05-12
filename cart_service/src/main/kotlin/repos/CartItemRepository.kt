package com.example.repos

import com.example.model.Availability
import com.example.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface CartItemRepository : JpaRepository<CartItem, UUID> {
    fun findByCartIdAndProductId(cartId: UUID, productId: UUID): CartItem?

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity  WHERE c.id = :id")
    fun updateQuantity(id: UUID, quantity: Int,): Int


    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.price = :price  WHERE c.id = :id")
    fun updatePrice(id: UUID, price: Double): Int


    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.availability = :availability WHERE c.id IN :ids")
    fun updateAvailabilityBatch(@Param("availability") availability: Availability, @Param("ids") ids: List<UUID>): Int

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.id IN :ids")
    fun deleteBatch(@Param("ids") ids: List<UUID>): Int

}