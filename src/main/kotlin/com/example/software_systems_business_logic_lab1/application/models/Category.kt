package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.key_classes.CategoryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("categories")
data class Category(
    @PrimaryKey val key: CategoryKey,
    val parentName: String? = null,
    val isParent: Boolean = true,
)