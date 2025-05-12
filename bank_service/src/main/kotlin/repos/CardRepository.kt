package com.example.repos

import com.example.model.Card
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import java.util.UUID


interface CardRepository : JpaRepository<Card, UUID> {
    fun findByCardNumber(cardNumber: String): Card?

    @Modifying
    @Transactional
    @Query("UPDATE Card c SET c.balance = :balance WHERE c.cardNumber = :cardNumber")
    fun updateCardBalance(
        @Param("cardNumber") cardNumber: String,
        @Param("balance") balance: Double
    ): Int
}
