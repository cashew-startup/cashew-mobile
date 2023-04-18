package com.cashew.features.receipt.domain

import kotlinx.datetime.Clock
import kotlin.jvm.JvmInline

data class Receipt(
    val id: ReceiptId,
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
) {
    companion object {
        fun mock(index: Int) = Receipt(
            id = ReceiptId(index.toLong()),
            company = "Company $index",
            address = "Harchenko street, $index",
            inn = "",
            date = Clock.System.now().toString(),
            receiptNumber = "$index",
            shift = "1",
            cashier = "Izyaslav Melentyev",
            total = (index * index).toDouble(),
            cash = index.toDouble(),
            card = 1.0,
            taxation = "null",
            products = Product.mocks()
        )

        fun mocks() = (0..5).map { mock(it) }
    }
}

@JvmInline
value class ReceiptId(val value: Long)