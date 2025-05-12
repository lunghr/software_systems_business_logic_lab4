package com.example.service


import com.example.kafka.ProductServiceProducer
import com.example.kafka.ResponseStorage
import com.example.model.*
import com.example.repos.CartItemRepository
import com.example.repos.CartRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import service.JwtService
import java.util.UUID

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val productServiceProducer: ProductServiceProducer,
    private val responseStorage: ResponseStorage,
    private val jwtService: JwtService
) {
    fun createCart(id: UUID): Cart {
        return cartRepository.save(Cart(userId = id))
    }

    @Transactional
    fun getListOfCartProductsForOrder(id: UUID): List<CartItem> {
        return cartRepository.findByUserId(id)?.items?.filter { it.availability == Availability.AVAILABLE }
            ?: throw CartNotFoundException()
    }

    fun addProductToCart(token: String, productId: UUID, quantity: Int): ResponseEntity<HttpStatus> {
        val cart = getCart(token)
        require(!isInCart(productId, cart)) { throw ProductAlreadyInCartException() }
        return checkAvailability(productId, quantity, HttpStatus.CREATED).let {
            cartItemRepository.save(
                CartItem(
                    cart = cart,
                    productId = productId,
                    quantity = quantity,
                    price = it.second
                )
            )
            ResponseEntity(HttpStatus.CREATED)
        }
    }

    fun incrementProductQuantity(token: String, productId: UUID): ResponseEntity<HttpStatus> {
        val cart = getCart(token)
        require(isInCart(productId, cart)) { throw ProductNotFoundException() }
        val cartItem = getCartItem(cart.id, productId)
        return checkAvailability(productId, 1 + cartItem.quantity, HttpStatus.OK).let {
            cartItemRepository.updateQuantity(cartItem.id, cartItem.quantity + 1)
            ResponseEntity(HttpStatus.OK)
        }
    }

    fun decrementProductQuantity(token: String, productId: UUID): ResponseEntity<HttpStatus> {
        val cart = getCart(token)
        require(isInCart(productId, cart)) { throw ProductNotFoundException() }
        val cartItem = getCartItem(cart.id, productId)
        if (cartItem.quantity - 1 == 0) {
            cartItemRepository.delete(cartItem)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        cartItemRepository.updateQuantity(cartItem.id, cartItem.quantity - 1)
        return ResponseEntity(HttpStatus.OK)
    }


    fun deleteProductFromCart(token: String, productId: UUID): ResponseEntity<HttpStatus> {
        val cart = getCart(token)
        require(isInCart(productId, cart)) { throw ProductNotFoundException() }
        val cartItem = getCartItem(cart.id, productId)
        cartItemRepository.delete(cartItem)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    private fun checkAvailability(productId: UUID, quantity: Int, status: HttpStatus): Pair<Boolean, Double> {
        val correlationId = UUID.randomUUID()
        productServiceProducer.sendProductAvailability(productId, quantity, correlationId)
        val response = responseStorage.waitForResponse(correlationId.toString())
        response?.let {
            when {
                it.enough && it.exists -> return Pair(true, it.price)
                !it.enough && it.exists -> throw NotEnoughProductException()
                else -> throw ProductNotFoundException()
            }
        } ?: throw ProductServiceException()
    }

    private fun isInCart(productId: UUID, cart: Cart): Boolean {
        println("cartId: ${cart.id}, productId: $productId")
        return cartItemRepository.findByCartIdAndProductId(cart.id, productId) != null
    }


    fun getCart(token: String): Cart {
        val userId = jwtService.extractId(jwtService.extractToken(token))
        return cartRepository.findByUserId(userId) ?: throw CartNotFoundException()
    }

    private fun getCartItem(cartId: UUID, productId: UUID): CartItem {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId)
            ?: throw ProductNotFoundException()
    }

    private fun getCartByUserId(id: UUID): Cart {
        return cartRepository.findByUserId(id) ?: throw CartNotFoundException()
    }

    @Transactional
    fun bookOrder(userId: UUID){
        val cart = getCartByUserId(userId)
        val ids = cart.items.map { it.id }
        cartItemRepository.updateAvailabilityBatch(Availability.BOOKED, ids)
    }
}
