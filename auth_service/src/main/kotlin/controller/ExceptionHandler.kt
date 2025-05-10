package com.example.controller

import com.example.model.InvalidDataException
import com.example.model.UserAlreadyExistsException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import com.example.model.UserNotFoundException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@CrossOrigin(origins = ["*"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<String> {
        logger.error("User not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException): ResponseEntity<String> {
        logger.error("User already exists: ${ex.message}")
        return ResponseEntity.status(409).body(ex.message)
    }

    @ExceptionHandler(InvalidDataException::class)
    fun handleInvalidDataException(ex: InvalidDataException): ResponseEntity<String> {
        return ResponseEntity.status(400).body(ex.message)
    }
}