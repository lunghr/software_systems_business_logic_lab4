package com.example.software_systems_business_logic_lab1.payment.bank.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "cards_info")
data class Card (
    @Id
    @Column(name = "card_number", unique = true)
    val cardNumber: String = String.format("%016d", kotlin.random.Random.nextLong(0, 1000000000000000000L)),
    @ManyToOne
    @JoinColumn(name="bank_account_id")
    val bankAccount: BankAccount,
    @Column(name = "expiration_date")
    val expirationDate: String = String.format("%02d/%02d", kotlin.random.Random.nextInt(1, 13), kotlin.random.Random.nextInt(26, 30)),
    @Column(name = "cvv")
    val cvv: String = String.format("%03d", kotlin.random.Random.nextInt(0, 1000)),
)