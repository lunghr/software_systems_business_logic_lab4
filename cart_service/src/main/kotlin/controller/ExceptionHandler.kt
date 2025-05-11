package com.example.controller

import com.example.model.*
import org.slf4j.LoggerFactory

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@CrossOrigin(origins = ["*"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(CartNotFoundException::class)
    fun handleOutOfStock(ex: CartNotFoundException): ResponseEntity<String> {
        logger.error("Cart not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(NotEnoughProductException::class)
    fun handleNotEnoughProductException(ex: NotEnoughProductException): ResponseEntity<String> {
        logger.error("Not enough product in stock: ${ex.message}")
        return ResponseEntity.status(400).body(ex.message)
    }

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(ex: ProductNotFoundException): ResponseEntity<String> {
        logger.error("Product not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(ProductServiceException::class)
    fun handleProductServiceException(ex: ProductServiceException): ResponseEntity<String> {
        logger.error("Product service is unavailable: ${ex.message}")
        return ResponseEntity.status(503).body(ex.message)
    }

    @ExceptionHandler(ProductAlreadyInCartException::class)
    fun handleProductAlreadyInCartException(ex: ProductAlreadyInCartException): ResponseEntity<String> {
        logger.error("Product already in cart: ${ex.message}")
        return ResponseEntity.status(409).body(ex.message)
    }

}