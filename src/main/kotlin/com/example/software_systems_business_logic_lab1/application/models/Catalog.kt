package com.example.software_systems_business_logic_lab1.application.models

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table("catalogs")
data class Catalog(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String
)