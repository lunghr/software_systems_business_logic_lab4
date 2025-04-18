package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Category
import org.springframework.data.cassandra.core.WriteResult
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface CategoryRepository : CassandraRepository<Category, UUID> {
    @Query("INSERT INTO categories (category_id, category_name, parentName, isParent) VALUES (?0, ?1, ?2, ?3) IF NOT EXISTS")
    fun saveIfNotExist(id: UUID, name: String, parentName: String?, isParent: Boolean): WriteResult

    @Query("SELECT * FROM categories WHERE category_name = ?0 ALLOW FILTERING")
    fun findByKeyName(name: String): Category?

    fun findCategoryByKeyId(id: UUID): Category?

    @Query("UPDATE categories SET isParent = ?2 WHERE category_id = ?0 and category_name = ?1 IF EXISTS")
    fun updateIsParentById(id: UUID, name: String, isParent: Boolean): Boolean

    @Query("SELECT * FROM categories WHERE parentName = ?0 ALLOW FILTERING")
    fun findByParentName(parentName: String): List<Category>

}