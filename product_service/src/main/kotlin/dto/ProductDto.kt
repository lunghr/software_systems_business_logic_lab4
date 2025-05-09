package com.example.dto

import com.example.model.Product
import com.example.model.key_class.ProductCategoryKey
import java.util.UUID


data class ProductDto(
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
    val categoryId: UUID,
) {
    fun toProduct(): Product {
        return Product(
            name = name,
            description = description,
            price = price,
            stockQuantity = stockQuantity,
            key = ProductCategoryKey(
                categoryId = categoryId,
            )
        )
    }
}