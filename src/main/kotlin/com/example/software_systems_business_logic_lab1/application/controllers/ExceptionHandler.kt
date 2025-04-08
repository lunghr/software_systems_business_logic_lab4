package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.models.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException::class)
    fun handleCategoryNotFound(ex: CategoryNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(NoParentCategoriesFoundException::class)
    fun handleNoParentFound(ex: NoParentCategoriesFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(CategoryAlreadyExistsException::class)
    fun handleCategoryAlreadyExists(ex: CategoryAlreadyExistsException): ResponseEntity<String> =
        ResponseEntity.status(409).body(ex.message)

    @ExceptionHandler(CategoryIsNotParentException::class)
    fun handleCategoryIsNotLeaf(ex: CategoryIsNotParentException): ResponseEntity<String> =
        ResponseEntity.status(403).body(ex.message)

    @ExceptionHandler(CategoryIsParentException::class)
    fun handleCategoryIsParent(ex: CategoryIsParentException): ResponseEntity<String> =
        ResponseEntity.status(403).body(ex.message)

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFound(ex: OrderNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(PaymentMethodNotFoundException::class)
    fun handlePaymentMethodNotFound(ex: PaymentMethodNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(InvalidCardDataException::class)
    fun handleInvalidCardData(ex: InvalidCardDataException): ResponseEntity<String> =
        ResponseEntity.status(400).body(ex.message)

    @ExceptionHandler(BankAccountCreationException::class)
    fun handleBankAccountCreation(ex: BankAccountCreationException): ResponseEntity<String> =
        ResponseEntity.status(503).body(ex.message)

    @ExceptionHandler(CardCreationException::class)
    fun handleCardCreation(ex: CardCreationException): ResponseEntity<String> =
        ResponseEntity.status(503).body(ex.message)

    @ExceptionHandler(BankAccountNotFoundException::class)
    fun handleBankAccountNotFound(ex: BankAccountNotFoundException): ResponseEntity<String> =
        ResponseEntity.status(404).body(ex.message)

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException): ResponseEntity<String> =
        ResponseEntity.status(409).body(ex.message)

    @ExceptionHandler(OutOfStockException::class)
    fun handleOutOfStock(ex: OutOfStockException): ResponseEntity<String> =
        ResponseEntity.status(409).body(ex.message)
}
//TODO 204, 404, 403, 503