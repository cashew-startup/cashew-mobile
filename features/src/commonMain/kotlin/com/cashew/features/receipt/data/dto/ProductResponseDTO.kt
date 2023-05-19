package com.cashew.features.receipt.data.dto

import com.cashew.features.receipt.domain.Product
import com.cashew.features.receipt.domain.ProductId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductResponseDTO(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("count") val count: String,
    @SerialName("sumPrice") val sumPrice: Double,
)

fun ProductResponseDTO.toDomain() = Product(
    id = ProductId(id),
    name = name,
    price = price,
    count = count,
    cost = sumPrice
)