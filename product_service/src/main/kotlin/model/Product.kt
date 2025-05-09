package com.example.model

import com.example.model.key_class.ProductCategoryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("products")
data class Product(
    @PrimaryKey val key: ProductCategoryKey,
    val name: String,
    val description: String,
    val price: Double,
    var stockQuantity: Int,
)