package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.Order
import com.example.software_systems_business_logic_lab1.application.models.OutOfStockException
import com.example.software_systems_business_logic_lab1.application.models.ProductNotFoundException
import com.example.software_systems_business_logic_lab1.application.repos.OrderProductRepository
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import org.springframework.stereotype.Service

@Service
class WarehouseService(
    private val productRepository: ProductRepository,
    private val orderProductRepository: OrderProductRepository
) {

    fun decreaseStockQuantity(order: Order){
        val items = orderProductRepository.findAllByKeyOrderId(order.id)
        items.forEach { item ->
            val product = productRepository.findProductByKeyProductId(item.key.productId) ?: throw ProductNotFoundException()

            if (product.stockQuantity < item.quantity){
                throw OutOfStockException()
            }


            productRepository.reduceStockQuantity(item.key.productId, product.key.categoryId, item.quantity)
        }
    }
}