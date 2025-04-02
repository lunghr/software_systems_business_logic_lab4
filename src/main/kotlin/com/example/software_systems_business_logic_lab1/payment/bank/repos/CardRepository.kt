package com.example.software_systems_business_logic_lab1.payment.bank.repos

import com.example.software_systems_business_logic_lab1.payment.bank.models.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID


interface CardRepository : JpaRepository<Card, UUID> {
    @Query("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber AND c.expirationDate = :expirationDate AND c.cvv = :cvv")
    fun findByCardDetails(cardNumber: String, expirationDate: String, cvv: String): Card?

}
