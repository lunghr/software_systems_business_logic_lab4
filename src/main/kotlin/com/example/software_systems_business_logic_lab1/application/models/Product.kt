package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.key_classes.ProductCategoryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("products")
data class Product(
    @PrimaryKey val key: ProductCategoryKey,
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
)