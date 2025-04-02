package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CatalogSubcatalogKey
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface SubcatalogRepository : CassandraRepository<Subcatalog, CatalogSubcatalogKey> {
    fun findByKeyCatalogName(catalogName: String): List<Subcatalog>


    @Query("SELECT * FROM subcatalogs WHERE subcatalog_name = ?0 ALLOW FILTERING")
    fun findByKeySubcatalogName(subcatalogName: String): Subcatalog?


    @Query("INSERT INTO subcatalogs (catalog_name, subcatalog_name) VALUES (?0, ?1) IF NOT EXISTS")
    fun saveIfNotExists(catalogName: String, subcatalogName: String): Boolean

}