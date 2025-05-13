package com.example.service

import com.example.dto.AuthRequestDto
import com.example.dto.RegisterRequestDto
import com.example.model.InvalidDataException
import com.example.model.User
import com.example.model.UserNotFoundException
import io.jsonwebtoken.Jwts
import model.UserRole
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import service.JwtService
import java.util.*
import kotlin.collections.HashMap

@Service
class AuthService(
    private var userService: UserService,
    private var authenticationManager: AuthenticationManager,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService
) {

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        if (userDetails is User) {
            claims["id"] = userDetails.id
            claims["email"] = userDetails.email
            claims["role"] = userDetails.role
        }
        return Jwts.builder()
            .subject(userDetails.username)
            .claims(claims)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(jwtService.signingKey)
            .compact()
    }

    fun registerUser(request: RegisterRequestDto): String {
        val user = User(
            email = request.email,
            phoneNumber = request.phoneNumber,
            username = request.username,
            role = UserRole.valueOf(request.role),
            password = passwordEncoder.encode(request.password)
        )

        userService.createUser(user)
        return generateToken(user)
    }


    fun login(request: AuthRequestDto): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        val authenticatedUser = userService.getUserByEmailOrPhone(request.username, request.username)
            ?: throw UserNotFoundException()

        return generateToken(authenticatedUser)
    }

    fun changeRole(role: String, userId: UUID): String {
        val userDetails = userService.getUserById(userId)
            ?: throw UserNotFoundException()

        val cleanRole = role.trim().replace("\"", "").uppercase()
        try {
            userDetails.role = UserRole.valueOf(cleanRole)
            userService.updateUserRole(userDetails)

            return userDetails.role.toString()
        } catch (e: Exception) {
            throw InvalidDataException()
        }


    }

}