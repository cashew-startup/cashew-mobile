package com.cashew.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginRequestDTO(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
)
@Serializable
class LoginResponseDTO(
    @SerialName("id") val id: String?,
    @SerialName("token") val token: TokenResponseDTO?
)