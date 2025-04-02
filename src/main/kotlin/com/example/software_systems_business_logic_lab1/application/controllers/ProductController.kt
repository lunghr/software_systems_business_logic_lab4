package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.services.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/product")
@Tag(name = "Controller for creating, updating and deleting products")
class ProductController(
    private val productService: ProductService
) {

    @Operation(
        summary = "Create a new product",
        description = "Creates a new product with the given details"
    )
    @PostMapping("/create")
    fun createProduct(
        @RequestBody productDto: ProductDto
    ): Product {
        return productService.createProduct(productDto)
    }

    @Operation(
        summary = "Change product stock quantity",
        description = "Changes the stock quantity of a product by id"
    )
    @PostMapping("/quantity/{productId}/change/{quantity}")
    fun changeProductStockQuantity(
        @Parameter(
            description = "Product id",
            example = "08150d93-1da7-41e8-b140-2a9341b60f6e"
        )
        @PathVariable productId: UUID,
        @Parameter(
            description = "Subcatalog name",
            example = "shoes"
        )
        @RequestBody subcatalogName: String,
        @Parameter(
            description = "New stock quantity",
            example = "10"
        )
        @PathVariable quantity: Int
    ) = productService.changeProductStockQuantity(productId, subcatalogName, quantity)


    @Operation(
        summary = "Reduce product stock quantity",
        description = "Reduces the stock quantity of a product by id"
    )
    @PostMapping("/quantity/{productId}/reduce/{quantity}")
    fun reduceProductStockQuantity(
        @Parameter(
            description = "Product id",
            example = "08150d93-1da7-41e8-b140-2a9341b60f6e"
        )
        @PathVariable productId: UUID,
        @Parameter(
            description = "Subcatalog name",
            example = "shoes"
        )
        @RequestBody subcatalogName: String,
        @Parameter(
            description = "Quantity to reduce",
            example = "5"
        )
        @PathVariable quantity: Int
    ) = productService.reduceProductStockQuantity(productId, subcatalogName, quantity)


}