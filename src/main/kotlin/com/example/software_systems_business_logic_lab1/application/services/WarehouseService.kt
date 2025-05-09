package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.CartProduct
import com.example.software_systems_business_logic_lab1.application.models.Order
import com.example.software_systems_business_logic_lab1.application.models.OutOfStockException
import com.example.software_systems_business_logic_lab1.application.models.ProductNotFoundException
import com.example.software_systems_business_logic_lab1.application.repos.OrderProductRepository

import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class WarehouseService(
//    private val productRepository: ProductRepository,
    private val orderProductRepository: OrderProductRepository,
    private val cassandraTemplate: CassandraTemplate
) {

//    fun decreaseStockQuantity(order: Order){
//        val items = orderProductRepository.findAllByKeyOrderId(order.id)
//        val batch = cassandraTemplate.batchOps()
//        items.forEach { item ->
//            val product = productRepository.findProductByKeyProductId(item.key.productId) ?: throw ProductNotFoundException()
//
//            if (product.stockQuantity < item.quantity){
//                throw OutOfStockException()
//            }
//            product.stockQuantity -= item.quantity
//            batch.update(product)
//        }
//        batch.execute()
//    }
}