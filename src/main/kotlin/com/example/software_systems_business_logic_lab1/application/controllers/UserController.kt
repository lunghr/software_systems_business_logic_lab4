package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.dto.UserDto
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.services.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "Controller for Users (in this project version it's kinda utility controller)")
class UserController(
    private val userService: UserService,
) {
    @Operation(
        summary = "Create a new user",
        description = "Creates a new user with the given details"
    )
    @PostMapping("/create")
    fun createUser(@RequestBody userDto: UserDto): User {
        return userService.createUser(userDto)
    }
}