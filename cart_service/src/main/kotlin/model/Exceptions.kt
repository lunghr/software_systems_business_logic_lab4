package com.example.model

class ProductAlreadyInCartException :
    RuntimeException("Product already in cart")

class CartNotFoundException :
    RuntimeException("Cart not found")

class OutOfStockException :
    RuntimeException("Product is out of stock")

class NotEnoughProductException :
    RuntimeException("Not enough product in stock")

class ProductNotFoundException :
    RuntimeException("Product not found")

class ProductServiceException :
    RuntimeException("Product service is unavailable")