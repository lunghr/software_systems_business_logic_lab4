package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.CatalogService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/catalog")
@Tag(name = "Controller for sorting and searching in catalog")
class CatalogController(
    private val catalogService: CatalogService,
) {

    @Operation(summary = "Create a new catalog")
    @PostMapping("/create/{catalogName}")
    fun createCatalog(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String
    ) {
        catalogService.createCatalog(catalogName)
    }

    @Operation(summary = "Create a new subcatalog")
    @PostMapping("/create/{catalogName}/{subcatalogName}")
    fun createSubcatalog(
        @Parameter(
            description = "Catalog name",
            example = "clothes"
        )
        @PathVariable catalogName: String,
        @Parameter(
            description = "Subcatalog name",
            example = "electronics"
        )
        @PathVariable subcatalogName: String
    ) {
        catalogService.createSubcatalog(catalogName, subcatalogName)
    }

}