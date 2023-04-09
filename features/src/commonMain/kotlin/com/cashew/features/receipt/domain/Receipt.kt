package com.cashew.features.receipt.domain

data class Receipt(
    val id: Long,
    val company: String,
    val address: String,
    val inn: String,
    val date: String,
    val receiptNumber: String,
    val shift: String,
    val cashier: String,
    val total: Double,
    val cash: Double,
    val card: Double,
    val taxation: String,
    val products: List<Product>
)