package com.cashew.features.authorization_flow.data

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.features.authorization_flow.data.dto.LoginRequestDTO
import com.cashew.features.authorization_flow.data.dto.LoginResponseDTO
import com.cashew.features.authorization_flow.data.dto.RegisterRequestDTO
import com.cashew.features.authorization_flow.data.dto.RegisterResponseDTO
import com.cashew.features.authorization_flow.data.storages.AccessTokenStorage
import com.cashew.features.authorization_flow.data.storages.RefreshTokenStorage
import com.cashew.features.authorization_flow.domain.LoginResult
import com.cashew.features.authorization_flow.domain.RegisterResult
import com.cashew.features.authorization_flow.domain.toDomain
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AuthorizationRepositoryImpl(
    private val httpClient: HttpClient,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage
) : AuthorizationRepository {

    override suspend fun login(username: String, password: String): LoginResult {
        val response = httpClient.post("/auth/login") {
            setBody(LoginRequestDTO(username, password))
        }.body<LoginResponseDTO>()
        accessTokenStorage.saveAccessToken(AccessToken(response.token.accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(response.token.refreshToken))
        return response.toDomain()
    }

    override suspend fun register(username: String, password: String): RegisterResult {
        val response = httpClient.post("/auth/register") {
            setBody(RegisterRequestDTO(username, password))
        }.body<RegisterResponseDTO>()
        accessTokenStorage.saveAccessToken(AccessToken(response.token.accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(response.token.refreshToken))
        return response.toDomain()
    }
}