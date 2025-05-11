package com.example.software_systems_business_logic_lab1.application.models


class OrderNotFoundException :
    RuntimeException("Order not found")

class PaymentMethodNotFoundException :
    RuntimeException("Payment method not found")

class PaymentMethodAlreadyExistsException :
    RuntimeException("Payment already added in your account")

class InvalidCardDataException :
    RuntimeException("Invalid card data")


class ProductAlreadyInCart :
    RuntimeException("Product already in cart")

class BankAccountCreationException :
    RuntimeException("Bank account creation failed")

class CardCreationException :
    RuntimeException("Card creation failed")

class BankAccountNotFoundException :
    RuntimeException("Bank account not found")

class CartNotFoundException :
    RuntimeException("Cart not found")

class OutOfStockException :
    RuntimeException("Product is out of stock")

