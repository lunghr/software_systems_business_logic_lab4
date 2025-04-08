package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.services.ProductService

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("/create")
    fun createProduct(
        @RequestBody productDto: ProductDto
    ): Product {
        return productService.createProduct(productDto)
    }

    @PostMapping("/quantity/{productId}/change/{quantity}")
    fun changeProductStockQuantity(
        @PathVariable productId: UUID,
        @PathVariable quantity: Int,
        @RequestBody categoryId:UUID
    ) = productService.changeProductStockQuantity(productId, categoryId, quantity)

    @PostMapping("/quantity/{productId}/reduce/{quantity}")
    fun reduceProductStockQuantity(
        @PathVariable productId: UUID,
        @RequestBody categoryId: UUID,
        @PathVariable quantity: Int
    ) = productService.reduceProductStockQuantity(productId, categoryId, quantity)


}