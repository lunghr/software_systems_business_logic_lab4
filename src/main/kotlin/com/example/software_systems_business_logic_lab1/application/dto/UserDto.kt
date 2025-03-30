package com.example.software_systems_business_logic_lab1.application.dto

import com.example.software_systems_business_logic_lab1.application.models.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "User Data Transfer Object for creating a new user")
data class UserDto (
    @Schema(description = "User full name", example = "Oleg Olegov")
    val username: String,
    @Schema(description = "User password", example = "password123")
    val password: String,
    @Schema(description = "User email", example = "test@gmail.com")
    val email: String,
    @Schema(description = "User phone number", example = "+1234567890")
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