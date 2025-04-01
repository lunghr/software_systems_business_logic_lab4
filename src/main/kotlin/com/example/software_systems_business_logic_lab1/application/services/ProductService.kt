package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun createProduct(productDto : ProductDto
    ): Product {
        return productRepository.save(productDto.toProduct())
    }

    fun getAllProductsBySubcatalog(subcatalogName: String): List<Product> {
        return productRepository.findByKeySubcatalogName(subcatalogName)
    }
}