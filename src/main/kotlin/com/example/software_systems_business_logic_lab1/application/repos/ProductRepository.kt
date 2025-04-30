package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.key_classes.ProductCategoryKey
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface ProductRepository : CassandraRepository<Product, ProductCategoryKey> {

    @Query("SELECT * FROM products WHERE product_id = ?0 ALLOW FILTERING")
    fun findProductByKeyProductId(productId: UUID): Product?

    fun findProductByKeyProductIdAndKeyCategoryId(productId: UUID, categoryID: UUID): Product?

    @Query("UPDATE products SET stockquantity = stockquantity - ?1 WHERE product_id = ?0 AND category_id = ?2 IF stockquantity >= ?1")
    fun reduceStockQuantity(productId: UUID, categoryID: UUID, quantity: Int): Boolean

    @Query("UPDATE products SET stockquantity = ?2 WHERE product_id = ?0 AND category_id = ?1 IF EXISTS")
    fun changeStockQuantity(productId: UUID, categoryID: UUID, quantity: Int): Boolean

    @Query("SELECT * FROM products WHERE product_id IN :productIds ALLOW FILTERING")
    fun findProductsByIds(productIds: List<UUID>): List<Product>

    fun findProductsByKeyCategoryId(categoryID: UUID): List<Product>




}