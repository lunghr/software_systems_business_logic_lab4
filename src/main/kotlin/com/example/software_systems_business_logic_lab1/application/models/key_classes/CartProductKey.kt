package com.example.software_systems_business_logic_lab1.application.models.key_classes

import com.example.software_systems_business_logic_lab1.application.models.CartProduct
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.UUID

@PrimaryKeyClass
data class CartProductKey(
    @PrimaryKeyColumn(name = "cart_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val cartId: UUID,

    @PrimaryKeyColumn(name = "product_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    val productId: UUID
)