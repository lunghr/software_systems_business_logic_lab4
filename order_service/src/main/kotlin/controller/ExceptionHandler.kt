package com.example.controller

import com.example.model.CartIsEmptyException
import com.example.model.CartServiceException
import com.example.model.OrderNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@CrossOrigin(origins = ["*"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(CartServiceException::class)
    fun handleCartServiceException(ex: CartServiceException): ResponseEntity<String> {
        logger.error("Cart service is unavailable: ${ex.message}")
        return ResponseEntity.status(503).body(ex.message)
    }

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(ex: OrderNotFoundException): ResponseEntity<String> {
        logger.error("Order not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }


}