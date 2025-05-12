package com.example.kafka

interface ResponseStorage {
    fun <T : Any> storeResponse(correlationId: String, response: T)
    fun <T : Any> waitForResponse(correlationId: String, timeoutSeconds: Long, clazz: Class<T>): T?
}
