package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Cart
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.repos.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
) {
    fun createCart(user: User): Cart {
        return cartRepository.save(Cart(user = user))
    }
}