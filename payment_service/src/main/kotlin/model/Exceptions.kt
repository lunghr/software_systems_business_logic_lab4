package model

class CardNotFoundException() : RuntimeException("Card not found")
class InvalidCardDetailsException() : RuntimeException("Invalid card details")
class BankServiceException() : RuntimeException("Bank service is unavailable")