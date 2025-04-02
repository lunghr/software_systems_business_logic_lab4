package com.example.software_systems_business_logic_lab1.application.controllers

import com.example.software_systems_business_logic_lab1.application.services.CartService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/cart")
@Tag(name = "Controller for operations in cart")
class CartController(
    private val cartService: CartService
) {

    @Operation(
        summary = "Get cart by id",
        description = "Returns cart by id"
    )
    @GetMapping("/get/{cartId}")
    fun getCart(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID
    ) = cartService.getCart(cartId)

    @Operation(
        summary = "Delete product from cart",
        description = "Deletes product from cart by id"
    )
    @DeleteMapping("/delete/{cartId}/{productId}")
    fun deleteProductFromCart(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID,
        @Parameter(
            description = "Product id",
            example = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"
        )
        @PathVariable productId: UUID
    ) = cartService.deleteProductFromCart(cartId, productId)


    @Operation(
        summary = "Decrement product from cart",
        description = "Decrements product from cart interface by id"
    )
    @PatchMapping("/decrement/{cartId}/{productId}")
    fun decrementProductFromCart(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID,
        @Parameter(
            description = "Product id",
            example = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"
        )
        @PathVariable productId: UUID
    ) = cartService.decrementProductQuantity(cartId, productId)


    @Operation(
        summary = "Increment product from cart",
        description = "Increments product from cart interface by id"
    )
    @PatchMapping("/increment/{cartId}/{productId}")
    fun incrementProductFromCart(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID,
        @Parameter(
            description = "Product id",
            example = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"
        )
        @PathVariable productId: UUID
    ) = cartService.incrementProductQuantity(cartId, productId)

    @Operation(
        summary = "Add product to cart",
        description = "Adds product to cart by id"
    )
    @PostMapping("/add/{cartId}/{productId}")
    fun addProductToCart(
        @Parameter(
            description = "Cart id",
            example = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"
        )
        @PathVariable cartId: UUID,
        @Parameter(
            description = "Product id",
            example = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"
        )
        @PathVariable productId: UUID
    ) = cartService.addProductToCart(cartId, productId)

}