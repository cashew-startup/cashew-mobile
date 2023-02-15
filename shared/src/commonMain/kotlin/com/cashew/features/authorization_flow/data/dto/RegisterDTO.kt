package com.cashew.features.authorization_flow.data.dto

@kotlinx.serialization.Serializable
class RegisterRequestDTO(
    val username: String,
    val password: String
)

@kotlinx.serialization.Serializable
class RegisterResponseDTO(
    val isSignUp: Boolean,
    val description: String,
    val token: TokenResponseDTO?
)