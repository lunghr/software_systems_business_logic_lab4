package com.example.software_systems_business_logic_lab1.payment.ozon_client.models

import com.example.software_systems_business_logic_lab1.application.models.Order
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "payment_transactions")
data class PaymentTransaction(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    @JsonBackReference
    val paymentMethod: PaymentMethod,

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    val order: Order,

    @Column(name="trasaction_status")
    val transactionStatus: TransactionStatus,

    @Column(name="transaction_date")
    val transactionDate: LocalDateTime = LocalDateTime.now(),
)