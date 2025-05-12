package com.example.kafka.tmp

import com.example.kafka.ResponseStorage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryResponseStorage : ResponseStorage {
    private val map = ConcurrentHashMap<String, ItemsResponse>()

    override fun storeResponse(correlationId: String, response: ItemsResponse) {
        map[correlationId] = response
        println("\uD83D\uDCA1 Stored response for correlationId: $response")
    }

    override fun waitForResponse(correlationId: String, timeoutSeconds: Long): ItemsResponse? {
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