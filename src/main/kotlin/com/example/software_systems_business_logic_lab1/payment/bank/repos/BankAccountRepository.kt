package com.example.software_systems_business_logic_lab1.payment.bank.repos

import com.example.software_systems_business_logic_lab1.payment.bank.models.BankAccount
import com.example.software_systems_business_logic_lab1.payment.bank.models.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BankAccountRepository : JpaRepository<BankAccount, String> {
    fun findByAccountNumber(accountNumber: String): BankAccount

    @Query("SELECT b FROM BankAccount b JOIN Card c ON b.accountNumber = c.bankAccount.accountNumber WHERE c.cardNumber = :cardNumber")
    fun findAccountByCardNumber(cardNumber: String): BankAccount?
}