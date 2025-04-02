package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.CartProduct
import com.example.software_systems_business_logic_lab1.application.models.Order
import com.example.software_systems_business_logic_lab1.application.models.enums.OrderPaymentStatus
import com.example.software_systems_business_logic_lab1.application.repos.OrderRepository
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val cartService: CartService,
    private val productService: ProductService,
    private val cassandraTemplate: CassandraTemplate,
    private val orderRepository: OrderRepository,
) {
    fun createOrder(cartId: UUID, products: List<UUID>): Order {
        products.forEach { println(it) }
        val cart = getOnlyValidProducts(cartService.getCart(cartId))
        val productsUUIDs = getListOfUUIDs(cart)
        require(products.all { it in productsUUIDs }) { "Some products in your cart are not available to order" }

        val orderProductsList = cart.filter { it.key.productId in products }
        val order = toOrder(cartService.getUser(cartId)).also {
            it.totalPrice = calculateTotalPrice(orderProductsList)
        }
        moveToOrder(cartId, orderProductsList, order.id)
        return orderRepository.save(order)
    }


    private fun moveToOrder(cartId: UUID, products: List<CartProduct>, orderId: UUID) {
        val batch = cassandraTemplate.batchOps()
        for (item in products) {
            val orderProduct = toOrderProduct(orderId, item.key.productId, item.quantity)
            val cartProduct = toCartProduct(cartId, item.key.productId, item.quantity)
            batch.insert(orderProduct)
            batch.delete(cartProduct)
        }
        batch.execute()
    }


    private fun calculateTotalPrice(productsList: List<CartProduct>): Double {
        val products = productService.getProductsByUUIds(getListOfUUIDs(productsList))
        return products.sumOf { product ->
            productsList.find { it.key.productId == product.key.productId }?.let { cartProduct ->
                product.price * cartProduct.quantity
            } ?: 0.0
        }
    }

    fun getOrderById(orderId: UUID): Order? {
        return orderRepository.findById(orderId).orElse(null)
    }

    fun changeOrderStatus(order: Order, status: OrderPaymentStatus): Order {
        order.orderPaymentStatus = status
        return orderRepository.save(order)
    }
}