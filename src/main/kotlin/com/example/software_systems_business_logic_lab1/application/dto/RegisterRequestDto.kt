package com.example.software_systems_business_logic_lab1.application.dto

data class RegisterRequestDto(
    var username: String,
    var email: String,
    var phoneNumber: String,
    var password: String,
    var role: String
)