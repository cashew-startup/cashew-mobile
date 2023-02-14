package com.cashew.features.authorization_flow.data.dto

@kotlinx.serialization.Serializable
class TokenResponseDTO(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)

@kotlinx.serialization.Serializable
class TokenRequestDTO(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)