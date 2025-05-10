package com.example.model

class UserAlreadyExistsException :
    RuntimeException("User with this email or phone already exists")

class UserNotFoundException :
    RuntimeException("User not found")

class InvalidDataException :
    RuntimeException("Invalid role")