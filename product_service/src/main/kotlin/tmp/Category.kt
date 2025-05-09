package com.example.tmp


import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("categories")
data class Category(
    @PrimaryKey val key: CategoryKey,
    val parentName: String? = null,
    val isParent: Boolean = true,
)