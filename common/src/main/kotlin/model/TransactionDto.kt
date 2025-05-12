package model

import java.util.UUID

data class TransactionDto(
    var cardExists: Boolean = false,
    var enoughMoney: Boolean = false,
    val orderId: UUID,
    val transactionId: UUID? = null,
    val transactionAmount: Double,
    val userId: UUID
)