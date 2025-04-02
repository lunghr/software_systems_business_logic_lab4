package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.services.CatalogService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/catalog")
@Tag(name = "Controller for sorting and searching in catalog")
class CatalogController(
    private val catalogService: CatalogService,
) {

    @Operation(
        summary = "Create a new catalog",
        description = "Creates a new catalog with the given name if catalog with this name does not exist"
    )
    @PostMapping("/create/{catalogName}")
    fun createCatalog(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String
    ): Catalog {
        return catalogService.createCatalog(catalogName)
    }


    @Operation(
        summary = "Create a new subcatalog",
        description = "Creates a new subcatalog with the given name in the given catalog if subcatalog with this name does not exist in only existing catalog"
    )
    @PostMapping("/create/{catalogName}/{subcatalogName}")
    fun createSubcatalog(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String,
        @Parameter(
            description = "Subcatalog name",
            example = "shoes"
        )
        @PathVariable subcatalogName: String
    ): Subcatalog =
        catalogService.createSubcatalog(catalogName, subcatalogName)

    @Operation(
        summary = "Get all catalogs",
        description = "Returns a list of all catalogs"
    )
    @GetMapping("/get/catalogs")
    fun getCatalogs(): List<String> {
        return catalogService.getCatalogs().map { it.name }
    }

    @Operation(
        summary = "Get all subcatalogs of a catalog",
        description = "Returns a list of all subcatalogs of a catalog"
    )
    @GetMapping("/get/catalogs/{catalogName}/subcatalogs")
    fun getSubcatalogs(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String
    ): List<String> {
        return catalogService.getSubcatalogsByCatalog(catalogName).map { it.key.subcatalogName }
    }

    @Operation(
        summary = "Get all products of a subcatalog",
        description = "Returns a list of all products of a subcatalog"
    )
    @GetMapping("/get/catalogs/{catalogName}/subcatalogs/{subcatalogName}/products")
    fun getProducts(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String,
        @Parameter(
            description = "Subcatalog name",
            example = "shoes"
        )
        @PathVariable subcatalogName: String
    ): List<Product> {
        return catalogService.getProductsBySubcatalog(subcatalogName)
    }

}