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

    @ExceptionHandler(CategoryNotFoundException::class)
    fun handleCategoryNotFound(ex: CategoryNotFoundException): ResponseEntity<String> {
        logger.error("Category not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(NoParentCategoriesFoundException::class)
    fun handleNoParentFound(ex: NoParentCategoriesFoundException): ResponseEntity<String> {
        logger.error("No parent categories found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(CategoryAlreadyExistsException::class)
    fun handleCategoryAlreadyExists(ex: CategoryAlreadyExistsException): ResponseEntity<String> {
        logger.error("Category already exists: ${ex.message}")
        return ResponseEntity.status(409).body(ex.message)
    }

    @ExceptionHandler(CategoryIsNotParentException::class)
    fun handleCategoryIsNotLeaf(ex: CategoryIsNotParentException): ResponseEntity<String> {
        logger.error("Category is not a parent: ${ex.message}")
        return ResponseEntity.status(403).body(ex.message)
    }

    @ExceptionHandler(CategoryIsParentException::class)
    fun handleCategoryIsParent(ex: CategoryIsParentException): ResponseEntity<String> {
        logger.error("Category is a parent: ${ex.message}")
        return ResponseEntity.status(403).body(ex.message)
    }

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

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<String> {
        logger.error("User not found: ${ex.message}")
        return ResponseEntity.status(404).body(ex.message)
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

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException): ResponseEntity<String> {
        logger.error("User already exists: ${ex.message}")
        return ResponseEntity.status(409).body(ex.message)
    }

    @ExceptionHandler(OutOfStockException::class)
    fun handleOutOfStock(ex: OutOfStockException): ResponseEntity<String> {
        logger.error("Product is out of stock: ${ex.message}")
        return ResponseEntity.status(400).body(ex.message)
    }
}