package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.services.CatalogService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/catalog")
class CatalogController(
    private val catalogService: CatalogService,
) {

    @PostMapping("/create/{catalogName}")
    fun createCatalog(
        @PathVariable catalogName: String
    ): Catalog {
        return catalogService.createCatalog(catalogName)
    }

    @PostMapping("/create/{catalogName}/{subcatalogName}")
    fun createSubcatalog(
        @PathVariable catalogName: String,
        @PathVariable subcatalogName: String
    ): Subcatalog =
        catalogService.createSubcatalog(catalogName, subcatalogName)

    @GetMapping("/get/catalogs")
    fun getCatalogs(): List<String> {
        return catalogService.getCatalogs().map { it.name }
    }

    @GetMapping("/get/catalogs/{catalogName}/subcatalogs")
    fun getSubcatalogs(
        @PathVariable catalogName: String
    ): List<String> {
        return catalogService.getSubcatalogsByCatalog(catalogName).map { it.key.subcatalogName }
    }

    @GetMapping("/get/catalogs/{catalogName}/subcatalogs/{subcatalogName}/products")
    fun getProducts(
        @PathVariable catalogName: String,
        @PathVariable subcatalogName: String
    ): List<Product> {
        return catalogService.getProductsBySubcatalog(subcatalogName)
    }

}