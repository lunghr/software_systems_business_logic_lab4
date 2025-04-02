package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.repos.CatalogRepository
import com.example.software_systems_business_logic_lab1.application.repos.SubcatalogRepository
import org.springframework.stereotype.Service

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
        return subcatalogRepository.findByKeyCatalogName(catalogName).takeIf { it.isNotEmpty() }
            ?: throw IllegalArgumentException("Catalog with name $catalogName does not exist")
    }

    fun getProductsBySubcatalog(subcatalogName: String): List<Product> {
        return productService.getAllProductsBySubcatalog(subcatalogName).takeIf { it.isNotEmpty() }
            ?: throw IllegalArgumentException("Subcatalog with name $subcatalogName does not exist")
    }

    fun createCatalog(name: String): Catalog =
        Catalog(name = name).takeIf { catalogRepository.saveIfNotExist(it.id, it.name) }
            ?: throw IllegalArgumentException("Catalog with name $name already exists")

    fun createSubcatalog(catalogName: String, subcatalogName: String): Subcatalog =
        catalogRepository.findByName(catalogName)?.let {
            toSubcatalog(catalogName, subcatalogName).takeIf {
                subcatalogRepository.saveIfNotExists(
                    it.key.catalogName,
                    it.key.subcatalogName
                )
            }
                ?: throw IllegalArgumentException("Subcatalog with name $subcatalogName already exists")
        } ?: throw IllegalArgumentException("Catalog with name $catalogName does not exist")
}
