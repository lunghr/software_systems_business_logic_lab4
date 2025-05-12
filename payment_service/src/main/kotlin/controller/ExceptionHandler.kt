package controller

import model.BankServiceException
import model.CardNotFoundException
import model.InvalidCardDetailsException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
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

    @ExceptionHandler(InvalidCardDetailsException::class)
    fun handleInvalidCardDetails(ex: InvalidCardDetailsException): ResponseEntity<String> {
        logger.error("Invalid card details: ${ex.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(BankServiceException::class)
    fun handleBankServiceException(ex: BankServiceException): ResponseEntity<String> {
        logger.error("Bank service is unavailable: ${ex.message}")
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.message)
    }

}