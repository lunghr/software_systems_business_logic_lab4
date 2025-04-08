package com.example.software_systems_business_logic_lab1.payment.bank.models

import com.example.software_systems_business_logic_lab1.payment.bank.models.enums.PaymentType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "bank_accounts")
data class BankAccount(
    @Id
    @Column(name = "bank_account_number", unique = true)
    val accountNumber: String = String.format("%019d", kotlin.random.Random.nextLong(0, 1000000000000000000L)),
    @Column(name = "balance")
    var balance: Double = 0.0,
    @Column(name = "payment_type")
    val paymentType: PaymentType = PaymentType.CARD,
)