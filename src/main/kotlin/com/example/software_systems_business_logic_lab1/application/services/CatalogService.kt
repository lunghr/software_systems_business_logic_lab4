package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.repos.CatalogRepository
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import com.example.software_systems_business_logic_lab1.application.repos.SubcatalogRepository
import org.springframework.stereotype.Service
import java.util.UUID
import javax.xml.catalog.CatalogManager.catalog

@Service
class CatalogService(
    private val catalogRepository: CatalogRepository,
    private val subcatalogRepository: SubcatalogRepository,
    private val productService: ProductService
) {

    fun getCatalogs(): List<Catalog> {
        return catalogRepository.findAll()
    }

    fun getSubcatalogs(): List<Subcatalog> {
        return subcatalogRepository.findAll()
    }

    fun getSubcatalogsByCatalog(catalogName: String): List<Subcatalog> {
        return subcatalogRepository.findByKeyCatalogName(catalogName)
    }

    fun getProductsBySubcatalog(subcatalogName: String): List<Product> {
        return productService.getAllProductsBySubcatalog(subcatalogName)
    }

    fun createCatalog(name: String): Catalog =
        Catalog(name = name).takeIf { catalogRepository.saveIfNotExist(it.id, it.name) }
            ?: throw IllegalArgumentException("Catalog with name $name already exists")

    fun createSubcatalog(catalogName: String, subcatalogName: String): Subcatalog {
        return subcatalogRepository.save(toSubcatalog(catalogName, subcatalogName))
    }

}