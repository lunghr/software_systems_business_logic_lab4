package com.example.model

class CartServiceException :
    RuntimeException("Cart service is unavailable")

class CartIsEmptyException :
    RuntimeException("Cart is empty")

