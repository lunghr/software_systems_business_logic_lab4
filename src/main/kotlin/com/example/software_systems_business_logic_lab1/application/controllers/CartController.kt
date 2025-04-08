package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.CartService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {
    @GetMapping("/get/{cartId}")
    fun getCart(
        @PathVariable cartId: UUID
    ) = cartService.getCart(cartId)

    @DeleteMapping("/delete/{cartId}/{productId}")
    fun deleteProductFromCart(
        @PathVariable cartId: UUID,
        @PathVariable productId: UUID
    ) = cartService.deleteProductFromCart(cartId, productId)

    @PatchMapping("/decrement/{cartId}/{productId}")
    fun decrementProductFromCart(
        @PathVariable cartId: UUID,
        @PathVariable productId: UUID
    ) = cartService.decrementProductQuantity(cartId, productId)

    @PatchMapping("/increment/{cartId}/{productId}")
    fun incrementProductFromCart(
        @PathVariable cartId: UUID,
        @PathVariable productId: UUID
    ) = cartService.incrementProductQuantity(cartId, productId)

    @PostMapping("/add/{cartId}/{productId}")
    fun addProductToCart(
        @PathVariable cartId: UUID,
        @PathVariable productId: UUID
    ) = cartService.addProductToCart(cartId, productId)

}