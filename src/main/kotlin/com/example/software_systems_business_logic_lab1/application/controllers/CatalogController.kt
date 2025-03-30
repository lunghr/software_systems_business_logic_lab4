package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import com.example.software_systems_business_logic_lab1.application.repos.CatalogRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalogs")
class CatalogController(
    private val catalogRepository: CatalogRepository
) {
    @PostMapping("/create")
    fun create(@RequestBody name: String) {
        catalogRepository.save(Catalog(name = name))
    }

    @GetMapping("/get")
    fun getAll(): List<Catalog> {
        return catalogRepository.findAll()
    }
}