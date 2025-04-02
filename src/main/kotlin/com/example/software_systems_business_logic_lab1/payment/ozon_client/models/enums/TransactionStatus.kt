package com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums

enum class TransactionStatus {
    CREATED,
    PENDING,
    COMPLETED,
    FAILED,
    CANCELLED;

    companion object {
        fun toBoolean(status: TransactionStatus): Boolean {
            return when (status) {
                CREATED, PENDING -> false
                COMPLETED, -> true
                FAILED, CANCELLED -> false
            }
        }
    }
}