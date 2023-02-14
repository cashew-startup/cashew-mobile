package com.cashew.features.authorization_flow.data.dto

@kotlinx.serialization.Serializable
class LoginResponseDTO(
    val isLogin: Boolean,
    val username: String,
    val token: TokenResponseDTO
)

@kotlinx.serialization.Serializable
class LoginRequestDTO(
    val username: String,
    val password: String
)