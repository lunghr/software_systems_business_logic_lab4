package com.example.controller

import com.example.dto.ProductDto
import com.example.model.Product
import com.example.service.ProductService
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