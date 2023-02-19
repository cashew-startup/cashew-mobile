package com.cashew.features.authorization_flow.data

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.storages.AccessTokenStorage
import com.cashew.core.network.authorization.storages.RefreshTokenStorage
import com.cashew.core.network.dto.LoginRequestDTO
import com.cashew.core.network.dto.LoginResponseDTO
import com.cashew.core.network.dto.RegisterRequestDTO
import com.cashew.core.network.dto.RegisterResponseDTO
import com.cashew.core.network.requests.RequestHandler
import io.ktor.client.request.*
import io.ktor.client.statement.*

class AuthorizationRepositoryImpl(
    private val requestHandler: RequestHandler,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage
) : AuthorizationRepository {

    override suspend fun login(username: String, password: String) {
        val response: LoginResponseDTO = requestHandler.request {
            val response = post("auth/login") {
                setBody(LoginRequestDTO(username, password))
            }
            println(response.bodyAsText())
            response
        }
        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(response.token.accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(response.token.refreshToken))

    }

    override suspend fun register(username: String, password: String) {
        val response: RegisterResponseDTO = requestHandler.request {
            post("auth/register") {
                setBody(RegisterRequestDTO(username, password))
            }
        }
        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))
    }
}