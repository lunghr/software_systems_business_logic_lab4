package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.CategoryIsParentException
import com.example.software_systems_business_logic_lab1.application.models.CategoryNotFoundException
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.repos.CategoryRepository
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService

) {

    fun createProduct(productDto: ProductDto): Product =
        categoryService.getCategoryById(productDto.categoryId)
            .takeIf { categoryService.isAvailableToAddProduct(it) }
            ?.let { productRepository.save(productDto.toProduct()) }
            ?: throw CategoryIsParentException(productDto.categoryId.toString())



    fun isAvailableToOrder(productId: UUID, quantity: Int): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let {
            it.stockQuantity > 0 && (it.stockQuantity - quantity) >= 0
        } ?: throw IllegalArgumentException("Product with id $productId does not exist")
    }

    fun changeProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        require(quantity >= 0) { "Quantity must be non-negative" }
        return productRepository.changeStockQuantity(productId, categoryId, quantity)
    }

    fun reduceProductStockQuantity(productId: UUID, categoryId: UUID, quantity: Int): Boolean {
        return productRepository.reduceStockQuantity(productId, categoryId, quantity)
    }

    fun isExists(productId: UUID): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let { true } ?: false
    }

    fun getProductsByUUIds(productIds: List<UUID>): List<Product> {
        return productRepository.findProductsByIds(productIds)
    }

}