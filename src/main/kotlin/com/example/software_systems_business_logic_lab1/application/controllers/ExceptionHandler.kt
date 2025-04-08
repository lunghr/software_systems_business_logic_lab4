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
}
//TODO 204, 404, 403, 503