package com.cashew.features.authorization_flow.domain

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.dto.RegisterResponseDTO

sealed class RegisterResult {
    data class Success(
        val accessToken: AccessToken,
        val refreshToken: RefreshToken
    ) : RegisterResult()

    data class Failure(
        val description: String
    ) : RegisterResult()
}

fun RegisterResponseDTO.toDomain(): RegisterResult {
    return if (this.token != null) {
        RegisterResult.Success(
            accessToken = AccessToken(this.token.accessToken),
            refreshToken = RefreshToken(this.token.refreshToken)
        )
    } else RegisterResult.Failure(description = this.description)
}
