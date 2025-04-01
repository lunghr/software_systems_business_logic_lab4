package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Catalog
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface CatalogRepository: CassandraRepository<Catalog, UUID> {
    @Query("INSERT INTO catalogs (id, name) VALUES (?0, ?1) IF NOT EXISTS")
    fun saveIfNotExist(id: UUID, name: String): Boolean

    fun findByName(name: String): Catalog?

}