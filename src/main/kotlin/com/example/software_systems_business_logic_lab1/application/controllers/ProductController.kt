package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.services.ProductService
import org.springframework.web.bind.annotation.*

import java.util.UUID

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = ["*"])
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("/create")
    fun createProduct(
        @RequestBody productDto: ProductDto
    ): Product {
        return productService.createProduct(productDto)
    }

 // I wrote this awkwardly just for easy testing
    @PostMapping("/quantity/{productId}/change/{quantity}/{categoryId}")
    fun changeProductStockQuantity(
        @PathVariable productId: UUID,
        @PathVariable categoryId: UUID,
        @PathVariable quantity: Int
 ) = productService.changeProductStockQuantity(productId, categoryId, quantity)

    @PostMapping("/quantity/{productId}/reduce/{quantity}/{categoryId}")
    fun reduceProductStockQuantity(
        @PathVariable productId: UUID,
        @PathVariable categoryId: UUID,
        @PathVariable quantity: Int
    ) = productService.reduceProductStockQuantity(productId, categoryId, quantity)


}