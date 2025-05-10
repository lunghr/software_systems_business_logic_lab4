package com.example.model


import com.example.model.key_class.CategoryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("categories")
data class Category(
    @PrimaryKey val key: CategoryKey,
    val parentName: String? = null,
    val isParent: Boolean = true,
)