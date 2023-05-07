package com.cashew.features.receipt.ui.details

import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Product

data class ProductViewData(
    val id: Long,
    val name: String,
    val price: Double,
    val count: String,
    val cost: Double,
    val payers: List<Friend>
)

fun Product.toViewData(payers: List<Friend>) = ProductViewData(
    id = this.id.value,
    name = this.name,
    price = this.price,
    count = this.count,
    cost = this.cost,
    payers = payers
)