package com.example.software_systems_business_logic_lab1.payment.bank.repos

import com.example.software_systems_business_logic_lab1.payment.bank.models.BankAccount
import org.springframework.data.jpa.repository.JpaRepository

interface BankAccountRepository : JpaRepository<BankAccount, String> {
    fun findByAccountNumber(accountNumber: String): BankAccount
}