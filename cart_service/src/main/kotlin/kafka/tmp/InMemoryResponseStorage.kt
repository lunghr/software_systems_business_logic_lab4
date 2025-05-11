package com.example.kafka.tmp

import com.example.kafka.ResponseStorage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryResponseStorage : ResponseStorage {
    private val map = ConcurrentHashMap<String, ProductAvailabilityResponse>()

    override fun storeResponse(correlationId: String, response: ProductAvailabilityResponse) {
        map[correlationId] = response
    }

    override fun waitForResponse(correlationId: String, timeoutSeconds: Long): ProductAvailabilityResponse? {
        val deadline = System.currentTimeMillis() + timeoutSeconds * 1000
        while (System.currentTimeMillis() < deadline) {
            map[correlationId]?.let {
                map.remove(correlationId)
                return it
            }
        }
        return null
    }
}