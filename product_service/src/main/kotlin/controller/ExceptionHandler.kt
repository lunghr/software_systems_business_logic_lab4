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

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(ex: ProductNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(404).body(ex.message)
    }
}