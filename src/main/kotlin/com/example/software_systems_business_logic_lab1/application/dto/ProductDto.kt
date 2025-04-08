package com.example.software_systems_business_logic_lab1.application.dto

import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.key_classes.ProductSubcatalogKey


data class ProductDto(
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
    val subcatalogName: String,
) {
    fun toProduct(): Product {
        return Product(
            name = name,
            description = description,
            price = price,
            stockQuantity = stockQuantity,
            key = ProductSubcatalogKey(
                subcatalogName = subcatalogName
            )
        )
    }
}