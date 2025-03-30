package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.key_classes.CartProductKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("cart_products")
data class CartProduct (
    @PrimaryKey val key: CartProductKey,
    val quantity: Int
)


