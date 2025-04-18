package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findUserById(id: UUID): User?

    fun findUserByEmailOrPhoneNumber(email: String, phone: String): User?


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :#{#user.username}, u.email = :#{#user.email}, u.phoneNumber = :#{#user.phoneNumber}, u.role = :#{#user.role} WHERE u.id = :#{#user.id}")
    fun update(user: User): Int
}