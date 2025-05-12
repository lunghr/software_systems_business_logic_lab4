package com.example.kafka

import com.example.kafka.tmp.ItemsResponse


interface ResponseStorage {
    fun storeResponse(correlationId: String, response: ItemsResponse)
    fun waitForResponse(correlationId: String, timeoutSeconds: Long = 5): ItemsResponse?
}