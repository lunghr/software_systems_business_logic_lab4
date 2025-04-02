package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.key_classes.OrderProductKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("order_products")
data class OrderProduct(
    @PrimaryKey val key: OrderProductKey,
    val quantity: Int
)