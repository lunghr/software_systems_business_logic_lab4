package com.example.repos

import com.example.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CartRepository : JpaRepository<Cart, UUID> {
    fun findByUserId(id: UUID): Cart?
    fun findCartById(id: UUID): Cart?
}