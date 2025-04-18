package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.dto.AuthRequestDto
import com.example.software_systems_business_logic_lab1.application.dto.RegisterRequestDto
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.models.UserNotFoundException
import com.example.software_systems_business_logic_lab1.application.models.enums.UserRole
import jakarta.persistence.Id
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthService(
    private var userService: UserService,
    private var authenticationManager: AuthenticationManager,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService
) {
    fun registerUser(request: RegisterRequestDto): String {
        val user = User(
            email = request.email,
            phoneNumber = request.phoneNumber,
            username = request.username,
            role = UserRole.valueOf(request.role),
            password = passwordEncoder.encode(request.password)
        )

//        println("${user.email}, ${user.phoneNumber}, ${user.password}, ${user.role}")

        userService.createUser(user)
        println("User created")
        return jwtService.generateToken(user)
    }


    fun login(request: AuthRequestDto): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        val authenticatedUser = userService.getUserByEmailOrPhone(request.username, request.username)
            ?: throw UserNotFoundException()

        return jwtService.generateToken(authenticatedUser)
    }

    fun changeRole(role: String, userId: UUID): String {
        val userDetails = userService.getUserById(userId)
            ?: throw UserNotFoundException()
        val cleanRole = role.trim().replace("\"", "").uppercase()
        userDetails.role = UserRole.valueOf(role)
        return userService.updateUserRole(userDetails).takeIf { it > 0 }?.let {
            userDetails.role.toString()
        } ?: throw UserNotFoundException()
    }

}