package com.cashew.features.receipt.data.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class ReceiptRequestDTO(
    @SerialName("username") val username: String,
    @SerialName("token") val receiptString: String
)
