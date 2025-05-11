package com.example.kafka

import com.example.kafka.tmp.ProductAvailabilityResponse

interface ResponseStorage {
    fun storeResponse(correlationId: String, response: ProductAvailabilityResponse)
    fun waitForResponse(correlationId: String, timeoutSeconds: Long = 5): ProductAvailabilityResponse?
}