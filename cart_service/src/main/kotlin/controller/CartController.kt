package com.example.controller

import com.example.service.CartService
import org.springframework.messaging.handler.annotation.Header
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = ["*"])
class CartController(
    private val cartService: CartService
) {
    @GetMapping("/get")
    fun getCart(
        @RequestHeader("Authorization") token: String,
    ) = cartService.getCart(token)

    @DeleteMapping("/delete/{productId}")
    fun deleteProductFromCart(
        @RequestHeader("Authorization") token: String,
        @PathVariable productId: UUID
    ) = cartService.deleteProductFromCart(token, productId)

    @PatchMapping("/decrement/{productId}")
    fun decrementProductFromCart(
        @PathVariable productId: UUID,
        @RequestHeader("Authorization") token: String,
    ) = cartService.decrementProductQuantity(token, productId)

    @PatchMapping("/increment/{productId}")
    fun incrementProductFromCart(
        @PathVariable productId: UUID,
        @RequestHeader("Authorization") token: String,
    ) = cartService.incrementProductQuantity(token, productId)

    @PostMapping("/add/{productId}")
    fun addProductToCart(
        @PathVariable productId: UUID,
        @RequestParam quantity: Int,
        @RequestHeader("Authorization") token: String,
    ) = cartService.addProductToCart(token, productId, quantity)
}