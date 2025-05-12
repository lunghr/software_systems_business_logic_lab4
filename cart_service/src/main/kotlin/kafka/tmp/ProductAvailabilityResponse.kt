package com.example.kafka.tmp

data class ProductAvailabilityResponse(
    val exists: Boolean,
    val enough: Boolean,
    val price: Double,
)
