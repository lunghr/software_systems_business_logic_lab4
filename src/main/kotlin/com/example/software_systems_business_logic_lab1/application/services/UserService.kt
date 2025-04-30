package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.models.UserAlreadyExistsException
import com.example.software_systems_business_logic_lab1.application.models.UserNotFoundException
import com.example.software_systems_business_logic_lab1.application.repos.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val cartService: CartService
) {

    fun createUser(user: User): User {
        try {
            return userRepository.save(user).also { cartService.createCart(it) }
        } catch (e: Exception) {
            throw UserAlreadyExistsException()
        }

    }

    fun getUserByEmailOrPhone(email: String, phone: String): User? {
        return userRepository.findUserByEmailOrPhoneNumber(email, phone)
    }

    fun updateUserRole(user: User) {
        val updated = userRepository.update(user)
        if (updated == 0) {
            throw UserNotFoundException()
        }
    }

    fun getUserById(id: UUID): User? {
        return userRepository.findUserById(id)
    }

}
