package com.cashew.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TokenResponseDTO(
    @SerialName("userId") val userId: String,
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String
)

@Serializable
class TokenRequestDTO(
    @SerialName("userId") val userId: String,
    @SerialName("refreshToken") val refreshToken: String
)