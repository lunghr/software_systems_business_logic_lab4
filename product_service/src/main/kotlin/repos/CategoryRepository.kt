package com.example.repos


import com.example.model.Category
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface CategoryRepository : CassandraRepository<Category, UUID> {
    @Query("SELECT * FROM categories WHERE category_name = ?0 ALLOW FILTERING")
    fun findByKeyName(name: String): Category?

    @Query("SELECT * FROM categories WHERE category_id = ?0 ALLOW FILTERING")
    fun findCategoryByKeyId(id: UUID): Category?

    @Query("UPDATE categories SET isParent = ?2 WHERE category_id = ?0 and category_name = ?1 IF EXISTS")
    fun updateIsParentById(id: UUID, name: String, isParent: Boolean): Boolean

    @Query("SELECT * FROM categories WHERE parentName = ?0 ALLOW FILTERING")
    fun findByParentName(parentName: String): List<Category>

}