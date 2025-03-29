package com.example.software_systems_business_logic_lab1.controllers

import com.example.software_systems_business_logic_lab1.models.Cart
import com.example.software_systems_business_logic_lab1.repos.CartRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cart")
class CartController(
     private val cartRepository: CartRepository,
    // private val cartService: CartService,
) {

    @PostMapping("/create")
    fun create(@RequestBody price: Double)
    {
        cartRepository.save(Cart(totalPrice = price))
    }


     @GetMapping("/get")
    fun getAll(): List<Cart>
    {
        return cartRepository.findAll()
    }

}