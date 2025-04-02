package com.example.software_systems_business_logic_lab1.payment.bank.services

import com.example.software_systems_business_logic_lab1.payment.bank.models.BankAccount
import com.example.software_systems_business_logic_lab1.payment.bank.models.Card
import com.example.software_systems_business_logic_lab1.payment.bank.repos.BankAccountRepository
import com.example.software_systems_business_logic_lab1.payment.bank.repos.CardRepository
import org.springframework.stereotype.Service

@Service
class BankService(
    private val cardRepository: CardRepository,
    private val bankAccountRepository: BankAccountRepository
) {

    fun createBankAccount(): BankAccount =
        try {
            bankAccountRepository.save(BankAccount())
        } catch (e: Exception) {
            throw RuntimeException("Failed to create bank account: ${e.message}")
        }

    fun createCard(accountNumber: String): Card =
        try {
            cardRepository.save(Card(bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)))
        } catch (e: Exception) {
            throw RuntimeException("Failed to create card: ${e.message}")
        }

    fun validateCard(cardNumber: String, expirationDate: String, cvv: String): Boolean {
        return cardRepository.findByCardDetails(
            cardNumber = cardNumber,
            expirationDate = expirationDate,
            cvv = cvv
        )?.let {
            true
        } ?: false
    }

    fun topUpBankAccount(cardNumber: String, amount: Double): Double {
        val acc = bankAccountRepository.findAccountByCardNumber(cardNumber)
            ?: throw RuntimeException("Bank account not found for card number: $cardNumber")
        return bankAccountRepository.save(acc.copy(balance = acc.balance + amount)).balance
    }

}