package com.cashew.features.authorization_flow.domain

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.features.authorization_flow.data.dto.RegisterResponseDTO

data class RegisterResult(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)

fun RegisterResponseDTO.toDomain() = RegisterResult(
    accessToken = AccessToken(this.token.accessToken),
    refreshToken = RefreshToken(this.token.refreshToken)
)
