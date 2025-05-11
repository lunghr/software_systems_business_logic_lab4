package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.models.*
import org.slf4j.LoggerFactory

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@CrossOrigin(origins = ["*"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)


    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFound(ex: OrderNotFoundException): ResponseEntity<String> {
        logger.error("Order not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(PaymentMethodNotFoundException::class)
    fun handlePaymentMethodNotFound(ex: PaymentMethodNotFoundException): ResponseEntity<String> {
        logger.error("Payment method not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(PaymentMethodAlreadyExistsException::class)
    fun handlePaymentMethodAlreadyExists (ex: PaymentMethodAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(409).body(ex.message)
    }


    @ExceptionHandler(InvalidCardDataException::class)
    fun handleInvalidCardData(ex: InvalidCardDataException): ResponseEntity<String> {
        logger.error("Invalid card data: ${ex.message}")
        return ResponseEntity.status(400).body(ex.message)
    }

    @ExceptionHandler(BankAccountCreationException::class)
    fun handleBankAccountCreation(ex: BankAccountCreationException): ResponseEntity<String> {
        logger.error("Bank account creation failed: ${ex.message}")
        return ResponseEntity.status(503).body(ex.message)
    }

    @ExceptionHandler(CardCreationException::class)
    fun handleCardCreation(ex: CardCreationException): ResponseEntity<String> {
        logger.error("Card creation failed: ${ex.message}")
        return ResponseEntity.status(503).body(ex.message)
    }

    @ExceptionHandler(BankAccountNotFoundException::class)
    fun handleBankAccountNotFound(ex: BankAccountNotFoundException): ResponseEntity<String> {
        logger.error("Bank account not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }


    @ExceptionHandler(OutOfStockException::class)
    fun handleOutOfStock(ex: OutOfStockException): ResponseEntity<String> {
        logger.error("Product is out of stock: ${ex.message}")
        return ResponseEntity.status(400).body(ex.message)
    }


    @ExceptionHandler(ProductAlreadyInCart::class)
    fun handleProductAlreadyInCartException(ex: ProductAlreadyInCart): ResponseEntity<String> {
        return ResponseEntity.status(409).body(ex.message)
    }

}