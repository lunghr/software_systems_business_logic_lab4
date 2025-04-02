package com.example.software_systems_business_logic_lab1.application.repos

import com.example.software_systems_business_logic_lab1.application.models.Product
import com.example.software_systems_business_logic_lab1.application.models.key_classes.ProductSubcatalogKey
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.UUID

interface ProductRepository : CassandraRepository<Product, ProductSubcatalogKey> {
    fun findByKeySubcatalogName(subcatalogName: String): List<Product>

    @Query("SELECT * FROM products WHERE product_id = ?0 ALLOW FILTERING")
    fun findProductByKeyProductId(productId: UUID): Product?

    @Query("UPDATE products SET stockquantity = stockquantity - ?1 WHERE product_id = ?0 AND subcatalog_name = ?2 IF stockquantity >= ?1")
    fun reduceStockQuantity(productId: UUID, subcatalogName: String, quantity: Int): Boolean

    @Query("UPDATE products SET stockquantity = ?2 WHERE product_id = ?0 AND subcatalog_name = ?1 IF EXISTS")
    fun changeStockQuantity(productId: UUID, subcatalogName: String, quantity: Int): Boolean

}