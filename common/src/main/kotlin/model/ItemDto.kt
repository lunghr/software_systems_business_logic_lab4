package model

import java.util.*
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ItemDto @JsonCreator constructor(
    @JsonProperty("productId") val productId: UUID,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("price") val price: Double
)
