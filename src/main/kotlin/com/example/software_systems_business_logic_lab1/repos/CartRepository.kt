package com.example.software_systems_business_logic_lab1.repos

import com.example.software_systems_business_logic_lab1.models.Cart
import org.hibernate.validator.constraints.UUID
import org.springframework.data.cassandra.repository.CassandraRepository

interface CartRepository : CassandraRepository<Cart, UUID> {
}