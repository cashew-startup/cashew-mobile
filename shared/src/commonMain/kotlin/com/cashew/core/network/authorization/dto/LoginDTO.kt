package com.cashew.core.network.authorization.dto

@kotlinx.serialization.Serializable
class LoginResponseDTO(
    val isLogin: Boolean,
    val username: String,
    val token: TokenResponseDTO?
)

@kotlinx.serialization.Serializable
class LoginRequestDTO(
    val username: String,
    val password: String
)