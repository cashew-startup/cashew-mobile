package com.cashew.features.receipt.domain

data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val count: String,
    val sumPrice: Double,
)
