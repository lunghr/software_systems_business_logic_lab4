package com.example.service

import com.example.dto.ProductDto
import com.example.model.CategoryIsParentException
import com.example.model.Product
import com.example.model.ProductNotFoundException
import com.example.repos.ProductRepository


import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private var productRepository: ProductRepository,
    private val categoryService: CategoryService,
) {

    fun createProduct(productDto: ProductDto): Product =
        categoryService.getCategoryById(productDto.categoryId).takeIf { categoryService.isAvailableToAddProduct(it) }
            ?.let {
                productRepository.save(productDto.toProduct())
            }
            ?: throw CategoryIsParentException(productDto.categoryId.toString())

    fun isAvailableToOrder(productId: UUID, quantity: Int): Triple<Boolean, Boolean, Double> {
        val product = productRepository.findProductByKeyProductId(productId)
            ?: return Triple(false, false, 0.0)
        return if (product.stockQuantity < quantity) Triple(true, false, 0.0) else Triple(true, true, product.price)
    }

    fun changeProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        require(quantity >= 0) { "Quantity must be non-negative" }
        return productRepository.changeStockQuantity(productId, categoryId, quantity)
    }

    fun reduceProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        val product = productRepository.findProductByKeyProductIdAndKeyCategoryId(productId, categoryId)
            ?: throw ProductNotFoundException()

        if (product.stockQuantity < quantity) {
            return false
        }

        productRepository.changeStockQuantity(productId, categoryId, product.stockQuantity - quantity)

        return true
    }


    fun isExists(productId: UUID): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let { true } ?: false
    }

    fun getProductsByUUIds(productIds: List<UUID>): List<Product> {
        return productRepository.findProductsByIds(productIds)
    }

    fun delete(product: Product) {
        productRepository.delete(product)
    }

}