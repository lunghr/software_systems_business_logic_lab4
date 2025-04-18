package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.dto.AuthRequestDto
import com.example.software_systems_business_logic_lab1.application.dto.RegisterRequestDto
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.services.AuthService
import com.example.software_systems_business_logic_lab1.application.services.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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