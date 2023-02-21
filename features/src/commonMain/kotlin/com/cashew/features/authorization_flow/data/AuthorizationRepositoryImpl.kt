package com.cashew.features.authorization_flow.data

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.storages.AccessTokenStorage
import com.cashew.core.network.authorization.storages.RefreshTokenStorage
import com.cashew.core.network.dto.LoginRequestDTO
import com.cashew.core.network.dto.LoginResponseDTO
import com.cashew.core.network.dto.RegisterRequestDTO
import com.cashew.core.network.dto.RegisterResponseDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AuthorizationRepositoryImpl(
    private val httpClient: HttpClient,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage
) : AuthorizationRepository {

    override suspend fun login(username: String, password: String) {
        val response: LoginResponseDTO = httpClient.post("auth/login") {
            setBody(LoginRequestDTO(username, password))
        }.body()

        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token?.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))

    }

    override suspend fun register(username: String, password: String) {
        val response: RegisterResponseDTO = httpClient.post("auth/register") {
            setBody(RegisterRequestDTO(username, password))
        }.body()

        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token?.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))
    }
}