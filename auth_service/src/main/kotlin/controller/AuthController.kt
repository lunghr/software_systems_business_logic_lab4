package com.example.controller

import com.example.dto.AuthRequestDto
import com.example.dto.RegisterRequestDto
import com.example.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["*"])
class AuthController(
    private var authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid authRequest: AuthRequestDto): String {
        return authService.login(authRequest)
    }

    @PostMapping("/register-user")
    fun register(@RequestBody @Valid registerRequest: RegisterRequestDto): String {
        return authService.registerUser(registerRequest)
    }

    @PostMapping("/change-role/{userId}")
    fun changeRole(@RequestBody @Valid role: String, @PathVariable userId: UUID): String {
        return authService.changeRole(role, userId)
    }
}