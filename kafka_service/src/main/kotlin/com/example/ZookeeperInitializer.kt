package com.example

import jakarta.annotation.PostConstruct
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.ZooDefs
import org.apache.zookeeper.ZooKeeper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class ZookeeperInitializer(
    @Value("\${API_KEY}") private val apiKey: String,
    @Value("\${CREATE_ORDER}") private val createdOrder: String,
    @Value("\${PAID_ORDER}") private val paidOrder: String,
    @Value("\${CANCELLED_ORDER}") private val cancelledOrder: String,
) {

    private lateinit var zk: ZooKeeper

    @PostConstruct
    fun init() {
        println("Connecting to ZooKeeper... ✅")
        zk = ZooKeeper("zookeeper:2181", 3000) { println("Connected: $it") }

        createIfNotExists("/mail")
        createOrUpdate("/mail/api_key", apiKey)

        createIfNotExists("/mail/templates")
        createOrUpdate("/mail/templates/created_order", createdOrder)
        createOrUpdate("/mail/templates/paid_order", paidOrder)
        createOrUpdate("/mail/templates/cancelled_order", cancelledOrder)

        zk.close()
    }

    private fun createIfNotExists(path: String) {
        if (zk.exists(path, false) == null) {
            zk.create(path, ByteArray(0), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        }
    }

    private fun createOrUpdate(path: String, value: String) {
        val data = value.toByteArray(StandardCharsets.UTF_8)
        if (zk.exists(path, false) == null) {
            zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        } else {
            zk.setData(path, data, -1)
        }
    }
}
