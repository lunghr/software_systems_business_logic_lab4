package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.dto.UserDto
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.repos.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val cartService: CartService
) {

    fun createUser(userDto: UserDto): User {
        try{
            return userRepository.save(userDto.toUser()).also { user -> cartService.createCart(user) }
        }
        catch (e: Exception) {
            throw RuntimeException("User with this email or phone already exists")
        }

    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getUserByPhone(phone: String): User? {
        return userRepository.findByPhoneNumber(phone)
    }

}