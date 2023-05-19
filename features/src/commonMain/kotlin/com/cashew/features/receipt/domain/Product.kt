package com.cashew.features.receipt.domain

import kotlin.jvm.JvmInline

data class Product(
    val id: ProductId,
    val name: String,
    val price: Double,
    val count: String,
    val cost: Double,
) {
    companion object {
        fun mock(index: Int) = Product(
            id = ProductId(index.toLong()),
            name = "Product $index",
            price = index.toDouble(),
            count = index.toString(),
            cost = (index * index).toDouble()
        )

        fun mocks() = (0..5).map { mock(it) }
    }
}

@JvmInline
value class ProductId(val value: Long)
