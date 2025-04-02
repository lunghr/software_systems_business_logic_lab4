package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.CartProduct
import com.example.software_systems_business_logic_lab1.application.models.Subcatalog
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CartProductKey
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CatalogSubcatalogKey
import java.util.UUID

fun toSubcatalog(catalogName: String, subcatalogName: String): Subcatalog {
    return Subcatalog(
        key = CatalogSubcatalogKey(
            subcatalogName = subcatalogName,
            catalogName = catalogName
        )
    )
}


fun toCartProduct(cartId: UUID, productId: UUID, quantity: Int): CartProduct {
    return CartProduct(
        key = CartProductKey(
            cartId = cartId,
            productId = productId
        ),
        quantity = quantity
    )
}