package com.example.software_systems_business_logic_lab1.application.models.key_classes

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.*


@PrimaryKeyClass
data class CategoryKey(
    @PrimaryKeyColumn(name = "category_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val name: String,

    @PrimaryKeyColumn(name = "category_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    val id: UUID = UUID.randomUUID()

)