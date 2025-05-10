package com.example.software_systems_business_logic_lab1.application.models

class CategoryNotFoundException(name: String) :
    RuntimeException("Category with name '$name' not found or has no children")

class NoParentCategoriesFoundException :
    RuntimeException("No parent categories found")

class CategoryAlreadyExistsException(name: String) :
    RuntimeException("Category with name '$name' already exists")

class CategoryIsNotParentException(name: String) :
    RuntimeException("Category with name '$name' is not a parent category")

class CategoryIsParentException(name: String) :
    RuntimeException("Category with name '$name' is a parent category and cannot be made ultimate")

class OrderNotFoundException :
    RuntimeException("Order not found")

class PaymentMethodNotFoundException :
    RuntimeException("Payment method not found")

class PaymentMethodAlreadyExistsException :
    RuntimeException("Payment already added in your account")

class InvalidCardDataException :
    RuntimeException("Invalid card data")

class ProductNotFoundException :
    RuntimeException("Product not found")

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

