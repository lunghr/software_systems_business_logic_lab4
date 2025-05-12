package com.example.repos

import com.example.model.PaymentTransaction
import com.example.model.enums.TransactionStatus
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface PaymentTransactionRepository : JpaRepository<PaymentTransaction, UUID> {


    @Modifying
    @Transactional
    @Query("UPDATE PaymentTransaction p SET p.transactionStatus = :transactionStatus WHERE p.id = :transactionId")
    fun updateTransactionStatus(
        @Param("transactionId") transactionId: UUID,
        @Param("transactionStatus") transactionStatus: TransactionStatus
    ): Int
}