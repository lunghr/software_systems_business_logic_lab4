package com.example.software_systems_business_logic_lab1.application.models.key_classes

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.UUID

@PrimaryKeyClass
data class ProductCategoryKey(

    @PrimaryKeyColumn(name = "product_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    val productId: UUID = UUID.randomUUID(),

    @PrimaryKeyColumn(name = "category_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    val categoryID: UUID
)
