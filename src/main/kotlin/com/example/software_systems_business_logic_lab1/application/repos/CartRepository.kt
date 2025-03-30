package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Cart
import org.hibernate.validator.constraints.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, UUID> {
}