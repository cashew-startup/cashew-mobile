package com.cashew.features.receipt.ui.details

import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Product
import com.cashew.features.receipt.domain.ProductId

data class ProductViewData(
    val id: ProductId,
    val name: String,
    val price: Double,
    val count: String,
    val cost: Double,
    val payers: List<Friend>,
    val allPayersCount: Int
)

fun Product.toViewData(payers: List<Friend>) = ProductViewData(
    id = this.id,
    name = this.name,
    price = this.price,
    count = this.count,
    cost = this.cost,
    payers = payers,
    allPayersCount = payers.size
)