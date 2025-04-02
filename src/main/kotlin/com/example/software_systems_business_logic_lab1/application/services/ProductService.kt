package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import com.example.software_systems_business_logic_lab1.application.repos.SubcatalogRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val subcatalogRepository: SubcatalogRepository
) {
    fun createProduct(
        productDto: ProductDto
    ): Product {
        return subcatalogRepository.findByKeySubcatalogName(productDto.subcatalogName)?.let {
            productRepository.save(productDto.toProduct())
        } ?: throw IllegalArgumentException("Subcatalog with name ${productDto.subcatalogName} does not exist")
    }

    fun getAllProductsBySubcatalog(subcatalogName: String): List<Product> {
        return productRepository.findByKeySubcatalogName(subcatalogName)
    }

    fun isAvailableToOrder(productId: UUID, quantity: Int): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let {
            it.stockQuantity > 0 && (it.stockQuantity - quantity) >= 0
        } ?: throw IllegalArgumentException("Product with id $productId does not exist")
    }

    fun changeProductStockQuantity(productId: UUID, subcatalogName: String, quantity: Int): Boolean {
        require(quantity >= 0) { "Quantity must be non-negative" }
        return productRepository.changeStockQuantity(productId, subcatalogName, quantity)
    }

    fun reduceProductStockQuantity(productId: UUID, subcatalogName: String, quantity: Int): Boolean {
        return productRepository.reduceStockQuantity(productId, subcatalogName, quantity)
    }

    fun isExists(productId: UUID): Boolean {
        return productRepository.findProductByKeyProductId(productId)?.let { true } ?: false
    }
}