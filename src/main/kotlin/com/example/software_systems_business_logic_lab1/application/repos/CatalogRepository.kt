package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.UUID

interface CatalogRepository: CassandraRepository<Catalog, UUID> {
}