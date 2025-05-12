package com.example.repos

import com.example.model.Order
import com.example.model.OrderStatus
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface OrderRepository:JpaRepository<Order, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :id")
    fun updateOrderStatus(@Param("id") id: UUID, @Param("status") status: OrderStatus): Int
}