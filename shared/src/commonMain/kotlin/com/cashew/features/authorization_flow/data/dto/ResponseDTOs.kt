package com.cashew.features.authorization_flow.data.dto

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import kotlinx.serialization.Serializable

@Serializable
class RegisterResponseDTO(
    val isRegister: Boolean,
    val description: String,
    val tokenDto: TokenResponseDTO
)

@Serializable
class LoginResponseDTO(
    val isLogin: Boolean,
    val username: String,
    val tokenDto: TokenResponseDTO
)

@Serializable
class TokenResponseDTO(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)