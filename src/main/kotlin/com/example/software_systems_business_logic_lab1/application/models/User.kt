package com.example.software_systems_business_logic_lab1.application.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "catalogs")
class User(
    @Id
    @Column(name = "user_id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "username", nullable = false)
    val username: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Column(name = "phone_number", nullable = false, unique = true)
    val phoneNumber: String
)
