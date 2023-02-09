package com.cashew.features.authorization_flow.data.dto

import kotlinx.serialization.Serializable

@Serializable
class RegisterRequestDTO(
    val username: String,
    val password: String
)

@Serializable
class LoginRequestDTO(
    val username: String,
    val password: String
)

@Serializable
class TokenRequestDTO(
    val userId: String,
    val refreshToken: String
)