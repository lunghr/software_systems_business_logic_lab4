package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CatalogSubcatalogKey
import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.UUID

interface SubcatalogRepository: CassandraRepository<Subcatalog, CatalogSubcatalogKey> {
    fun findByKeyCatalogName(catalogName: String): List<Subcatalog>
}