package com.example.software_systems_business_logic_lab1.application.dto

import com.example.software_systems_business_logic_lab1.application.models.User

data class UserDto (
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String
){
    fun toUser(): User {
        return User(
            username = username,
            password = password,
            email = email,
            phoneNumber = phoneNumber
        )
    }
}