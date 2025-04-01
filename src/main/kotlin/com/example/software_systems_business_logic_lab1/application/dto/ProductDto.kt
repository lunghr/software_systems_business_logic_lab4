package com.example.software_systems_business_logic_lab1.application.dto

import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.key_classes.ProductSubcatalogKey
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Product Data Transfer Object for creating a new product or updating an existing one")
data class ProductDto(
    @Schema(description = "Product name", example = "Laptop")
    val name: String,
    @Schema(description = "Product description", example = "High-performance laptop")
    val description: String,
    @Schema(description = "Product price", example = "999.99")
    val price: Double,
    @Schema(description = "Product stock quantity", example = "10000")
    val stockQuantity: Int,
    @Schema(description = "Product subcatalog name", example = "shoes")
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