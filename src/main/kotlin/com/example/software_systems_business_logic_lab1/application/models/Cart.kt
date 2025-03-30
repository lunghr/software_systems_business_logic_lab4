package com.example.software_systems_business_logic_lab1.application.models


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.NoArgsConstructor
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn

import java.util.UUID


@Entity
@Table(name = "carts")
@NoArgsConstructor
class Cart(
    @Id
    @Column(name = "cart_id")
    val id: UUID = UUID.randomUUID(),
    @OneToOne
    @PrimaryKeyColumn(name = "user_id")
    val user: User
)