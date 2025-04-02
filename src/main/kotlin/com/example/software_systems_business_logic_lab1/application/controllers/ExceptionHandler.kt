//package com.example.software_systems_business_logic_lab1.application.controllers
//
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.bind.annotation.ExceptionHandler
//
//@ControllerAdvice
//class ExceptionHandler {
//
//    @ExceptionHandler(Exception::class)
//    fun handleException(exception: Exception): ResponseEntity<String> {
//        return ResponseEntity.status(500).body("An error occurred: ${exception.message}")
//    }
//}