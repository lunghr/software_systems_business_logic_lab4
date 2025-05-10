package com.example.service

import com.example.model.User
import com.example.model.UserAlreadyExistsException
import com.example.model.UserNotFoundException
import com.example.repos.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun createUser(user: User): User {
        try {
            return userRepository.save(user)
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

    fun delete(user: User){
        userRepository.delete(user)
    }

}
