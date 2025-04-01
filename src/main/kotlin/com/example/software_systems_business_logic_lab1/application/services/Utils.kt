package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CatalogSubcatalogKey

fun toSubcatalog(subcatalogName: String, catalogName: String): Subcatalog {
    return Subcatalog(
        key = CatalogSubcatalogKey(
            subcatalogName = subcatalogName,
            catalogName = catalogName
        )
    )
}