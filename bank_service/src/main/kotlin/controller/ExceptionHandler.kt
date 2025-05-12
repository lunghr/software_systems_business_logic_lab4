package com.example.controller

import com.example.model.CardNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
@CrossOrigin(origins = ["*"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(CardNotFoundException::class)
    fun handleCardNotFound(ex: CardNotFoundException): ResponseEntity<String> {
        logger.error("Card not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }
}