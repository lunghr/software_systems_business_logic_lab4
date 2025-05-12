package com.example.repos

import com.example.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository:JpaRepository<Order, UUID> {
}