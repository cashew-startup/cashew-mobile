package com.cashew.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class StatusResponseDTO(
    @SerialName("code") val code: Int,
    @SerialName("description") val description: String
)