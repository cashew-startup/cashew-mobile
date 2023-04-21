package com.cashew.core.authorization

import com.cashew.core.network.dto.LoginRequestDTO
import com.cashew.core.network.dto.LoginResponseDTO
import com.cashew.core.network.dto.TokenRequestDTO
import com.cashew.core.network.dto.TokenResponseDTO
import com.cashew.core.storage.providers.CredentialsProvider
import com.cashew.core.storage.storages.AccessTokenStorage
import com.cashew.core.storage.storages.RefreshTokenStorage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

class TokenRefresher(
    private val refreshTokenStorage: RefreshTokenStorage,
    private val accessTokenStorage: AccessTokenStorage,
    private val credentialsProvider: CredentialsProvider
) {

    suspend fun refreshTokens(client: HttpClient): Boolean {
        return refreshByRefreshToken(client) || refreshByCredentials(client)
    }

    suspend fun refreshByCredentials(unauthorizedHttpClient: HttpClient): Boolean {
        val credentials = credentialsProvider.getCredentials() ?: return false

        val response = unauthorizedHttpClient.post("/auth/login") {
            setBody(
                LoginRequestDTO(
                    username = credentials.username.value,
                    password = credentials.password.value
                )
            )
        }.body<LoginResponseDTO>()

        val newAccessToken = response.token.accessToken
        val newRefreshToken = response.token.refreshToken
        saveTokens(newAccessToken, newRefreshToken)
        return true
    }

    suspend fun refreshByRefreshToken(client: HttpClient): Boolean {
        val refreshToken = refreshTokenStorage.getRefreshToken() ?: return false
        val userId = credentialsProvider.getUserId() ?: return false
        return try {
            val response = client.post("/auth/token/refresh") {
                setBody(
                    TokenRequestDTO(
                        userId = userId.value,
                        refreshToken = refreshToken.value
                    )
                )
            }.body<TokenResponseDTO>()

            val newAccessToken = response.accessToken
            val newRefreshToken = response.refreshToken

            saveTokens(newAccessToken, newRefreshToken)
            true
        } catch (e: ClientRequestException) {
            if (e.response.status != HttpStatusCode.NotFound) throw e
            false
        }
    }

    private suspend fun saveTokens(accessToken: String, refreshToken: String) {
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))
    }
}