package com.example.dto

data class RegisterRequestDto(
    var username: String,
    var email: String,
    var phoneNumber: String,
    var password: String,
    var role: String
)