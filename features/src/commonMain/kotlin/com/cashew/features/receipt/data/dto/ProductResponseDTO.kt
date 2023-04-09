package com.cashew.features.receipt.data.dto

import com.cashew.features.receipt.domain.Product
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
    id = id,
    name = name,
    price = price,
    count = count,
    sumPrice = sumPrice
)