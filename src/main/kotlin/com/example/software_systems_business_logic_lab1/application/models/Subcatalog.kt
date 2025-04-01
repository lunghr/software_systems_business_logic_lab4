package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.key_classes.CatalogSubcatalogKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("subcatalogs")
data class Subcatalog(
    @PrimaryKey val key: CatalogSubcatalogKey,
    val id: UUID = UUID.randomUUID(),
)