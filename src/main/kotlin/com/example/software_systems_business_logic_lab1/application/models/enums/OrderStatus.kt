package com.example.software_systems_business_logic_lab1.application.models.enums

enum class OrderStatus {
    WAITING_FOR_PAYMENT,
    WAITING_FOR_SHIPPING,
    IN_PROGRESS,
    DELIVERED,
    CANCELED,
    RETURNED,
    FAILED
}