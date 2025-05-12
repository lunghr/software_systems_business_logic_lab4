package com.example.kafka.tmp

import com.example.kafka.ResponseStorage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryResponseStorage : ResponseStorage {
    private val map = ConcurrentHashMap<String, Any>()

    override fun <T : Any> storeResponse(correlationId: String, response: T) {
        map[correlationId] = response
    }

    override fun <T : Any> waitForResponse(correlationId: String, timeoutSeconds: Long, clazz: Class<T>): T? {
        val deadline = System.currentTimeMillis() + timeoutSeconds * 1000
        while (System.currentTimeMillis() < deadline) {
            val value = map[correlationId]
            if (value != null && clazz.isInstance(value)) {
                map.remove(correlationId)
                return clazz.cast(value)
            }
        }
        return null
    }
}