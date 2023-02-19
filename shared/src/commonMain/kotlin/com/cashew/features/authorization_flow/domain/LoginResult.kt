package com.cashew.features.authorization_flow.domain

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.dto.LoginResponseDTO

sealed class LoginResult {
    data class Success(
        val accessToken: AccessToken,
        val refreshToken: RefreshToken
    ) : LoginResult()

    data class Failure(
        val description: String
    ) : LoginResult()
}

fun LoginResponseDTO.toDomain(): LoginResult {
    return if (this.token != null) {
        LoginResult.Success(
            accessToken = AccessToken(this.token.accessToken),
            refreshToken = RefreshToken(this.token.refreshToken)
        )
    } else LoginResult.Failure(description = this.username)
}