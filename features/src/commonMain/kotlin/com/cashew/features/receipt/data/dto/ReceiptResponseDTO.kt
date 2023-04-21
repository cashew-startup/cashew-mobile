package com.cashew.features.receipt.data.dto

import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.domain.ReceiptId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ReceiptResponseDTO(
    @SerialName("id") val id: Long,
    @SerialName("company") val company: String,
    @SerialName("address") val address: String,
    @SerialName("INN") val inn: String,
    @SerialName("date") val date: String,
    @SerialName("receiptNumber") val receiptNumber: String,
    @SerialName("shift") val shift: String,
    @SerialName("cashier") val cashier: String,
    @SerialName("total") val total: Double,
    @SerialName("cash") val cash: Double,
    @SerialName("card") val card: Double,
    @SerialName("taxation") val taxation: String,
    @SerialName("products") val products: List<ProductResponseDTO>
)

fun ReceiptResponseDTO.toDomain() = Receipt(
    id = ReceiptId(id),
    name = "Receipt",
    company = company,
    address = address,
    inn = inn,
    date = date,
    receiptNumber = receiptNumber,
    shift = shift,
    cashier = cashier,
    total = total,
    cash = cash,
    card = card,
    taxation = taxation,
    products = products.map { it.toDomain() }
)