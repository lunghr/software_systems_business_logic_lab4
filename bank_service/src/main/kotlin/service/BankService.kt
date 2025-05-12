package com.example.service

import com.example.model.Card
import com.example.model.CardNotFoundException
import com.example.repos.CardRepository
import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BankService(
    private val cardRepository: CardRepository,
) {
    fun createCard(): Card = cardRepository.save(Card())

    fun validateCard(cardNumber: String, expirationDate: String, cvv: String): Boolean =
        getCard(cardNumber).let { it.cvv == cvv && it.expirationDate == expirationDate }

    fun topUpBalance(cardNumber: String, amount: Double): ResponseEntity<Double> {
        val card = getCard(cardNumber)
        cardRepository.updateCardBalance(cardNumber, card.balance + amount)
        return ResponseEntity.ok(card.balance + amount)
    }

    @Transactional
    fun processTransaction(cardNumber: String, transactionAmount: Double): Boolean {
        val card = getCard(cardNumber)
        if (card.balance >= transactionAmount) {
            cardRepository.updateCardBalance(cardNumber, card.balance - transactionAmount)
            return true
        }
        return false
    }

    private fun getCard(cardNumber: String): Card {
        return cardRepository.findByCardNumber(cardNumber)
            ?: throw CardNotFoundException()
    }
}