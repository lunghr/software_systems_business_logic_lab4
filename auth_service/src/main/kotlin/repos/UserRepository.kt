package com.example.repos

import com.example.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findUserById(id: UUID): User?

    fun findUserByEmailOrPhoneNumber(email: String, phone: String): User?

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :#{#user.username}, u.email = :#{#user.email}, u.phoneNumber = :#{#user.phoneNumber}, u.role = :#{#user.role} WHERE u.id = :#{#user.id}")
    fun update(user: User): Int
}