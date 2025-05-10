package com.example.service

import com.example.dto.ProductDto
import com.example.model.Product
import com.example.repos.ProductRepository

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository, private val categoryService: CategoryService,
    private val productEventPublisher: ProductEventPublisher

) {

    fun createProduct(productDto: ProductDto): Product =
        categoryService.getCategoryById(productDto.categoryId).takeIf { categoryService.isAvailableToAddProduct(it) }
            ?.let {
                productRepository.save(productDto.toProduct()).also {
                    productEventPublisher.send(it)
                }
            }
//            ?: throw CategoryIsParentException(productDto.categoryId.toString())
            ?: throw RuntimeException("Category is parent")

    fun isAvailableToOrder(productId: UUID, quantity: Int): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let {
            it.stockQuantity > 0 && (it.stockQuantity - quantity) >= 0
//        } ?: throw ProductNotFoundException()
        } ?: false
    }

    fun changeProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        require(quantity >= 0) { "Quantity must be non-negative" }
        return productRepository.changeStockQuantity(productId, categoryId, quantity)
    }

    fun reduceProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        val product = productRepository.findProductByKeyProductIdAndKeyCategoryId(productId, categoryId)
//            ?: throw ProductNotFoundException()
            ?: throw RuntimeException("Product not found")

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