package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Cart
import com.example.software_systems_business_logic_lab1.application.models.CartProduct
import com.example.software_systems_business_logic_lab1.application.models.User
import com.example.software_systems_business_logic_lab1.application.repos.CartProductRepository
import com.example.software_systems_business_logic_lab1.application.repos.CartRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val cartProductRepository: CartProductRepository,
    private val productService: ProductService
) {
    fun createCart(user: User): Cart {
        return cartRepository.save(Cart(user = user))
    }

    fun addProductToCart(cartId: UUID, productId: UUID): CartProduct {
        return cartRepository.findCartById(cartId)?.let {
            toCartProduct(cartId, productId, 1).takeIf {
                productService.isExists(productId) &&
                        cartProductRepository.saveIfNotExists(
                            cartId,
                            productId,
                            1
                        )
            }
                ?: throw IllegalArgumentException("Product with id $productId already exists in cart or does not exist or out of stock")
        } ?: throw IllegalArgumentException("Cart with id $cartId does not exist")
    }

    //TODO add check of amount of product in stock like quantity + 1 <= stock_quantity
    fun incrementProductQuantity(cartId: UUID, productId: UUID): CartProduct {
        return cartProductRepository.findCartProductByKeyCartIdAndKeyProductId(cartId, productId)?.let { cartProduct ->
            cartProductRepository.save(
                cartProduct.copy(quantity = cartProduct.quantity + 1)
                    .takeIf { productService.isAvailableToOrder(productId, 1) }
                    ?: throw IllegalArgumentException("Product is out of stock")
            )
        } ?: throw IllegalArgumentException("Product with id $productId does not exist in cart with id $cartId")

    }

    fun decrementProductQuantity(cartId: UUID, productId: UUID): Int {
        return cartProductRepository.findCartProductByKeyCartIdAndKeyProductId(cartId, productId)?.let { cartProduct ->
            cartProductRepository.save(cartProduct.copy(quantity = cartProduct.quantity - 1)).quantity.takeIf {
                it > 0
            } ?: run {
                cartProductRepository.delete(cartProduct)
                0
            }
        } ?: throw IllegalArgumentException("Product with id $productId does not exist in cart with id $cartId")

    }

    fun deleteProductFromCart(cartId: UUID, productId: UUID): CartProduct {
        return cartProductRepository.findCartProductByKeyCartIdAndKeyProductId(cartId, productId)?.let { cartProduct ->
            cartProductRepository.delete(cartProduct)
            cartProduct
        } ?: throw IllegalArgumentException("Product with id $productId does not exist in cart with id $cartId")
    }

    fun getCart(cartId: UUID): List<CartProduct> {
        require(cartRepository.existsById(cartId)) { "Cart with id $cartId does not exist" }
        val cart = cartProductRepository.findByKeyCartId(cartId)
        return cart.map {
            it.takeIf { productService.isAvailableToOrder(it.key.productId, it.quantity) } ?: it.copy(
                quantity = -1
            )
        }
    }

    fun getValidCartProductUUIDs(cartId: UUID): List<UUID> {
        return cartProductRepository.findByKeyCartId(cartId).map { it.key.productId }
            .filter { productService.isAvailableToOrder(it, 1) }
    }

    fun getUser(cartId: UUID): User {
        return cartRepository.findCartById(cartId)?.user
            ?: throw IllegalArgumentException("Cart with id $cartId does not exist")
    }
}
