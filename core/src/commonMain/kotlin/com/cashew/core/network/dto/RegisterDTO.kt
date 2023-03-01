package com.cashew.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegisterRequestDTO(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
)

@Serializable
class RegisterResponseDTO(
    @SerialName("id") val id: String?,
    @SerialName("token") val token: TokenResponseDTO?
)