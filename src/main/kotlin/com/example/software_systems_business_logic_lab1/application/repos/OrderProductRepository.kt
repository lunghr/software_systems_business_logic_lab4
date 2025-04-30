package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.OrderProduct
import com.example.software_systems_business_logic_lab1.application.models.key_classes.OrderProductKey
import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.*

interface OrderProductRepository: CassandraRepository<OrderProduct, OrderProductKey> {
    fun findAllByKeyOrderId(orderId: UUID): List<OrderProduct>
}