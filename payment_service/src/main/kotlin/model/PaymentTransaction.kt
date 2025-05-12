package com.example.model

import com.example.model.enums.TransactionStatus
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "payment_transactions")
data class PaymentTransaction(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    val paymentMethod: PaymentMethod,

    @Column(name = "order_id")
    val orderId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(name="transaction_status")
    var transactionStatus: TransactionStatus,

    @Column(name="transaction_date")
    val transactionDate: LocalDateTime = LocalDateTime.now(),

    @Column(name="transaction_amount")
    val transactionAmount: Double
)