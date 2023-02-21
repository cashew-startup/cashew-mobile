package com.cashew.features.authorization_flow.data

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.dto.LoginRequestDTO
import com.cashew.core.network.dto.LoginResponseDTO
import com.cashew.core.network.dto.RegisterRequestDTO
import com.cashew.core.network.dto.RegisterResponseDTO
import com.cashew.core.network.requests.RequestHandler
import com.cashew.core.storage.storages.AccessTokenStorage
import com.cashew.core.storage.storages.RefreshTokenStorage
import io.ktor.client.request.*

class AuthorizationRepositoryImpl(
    private val requestHandler: RequestHandler,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage
) : AuthorizationRepository {

    override suspend fun login(username: String, password: String) {
        val response: LoginResponseDTO = requestHandler.request {
            post("auth/login") {
                setBody(LoginRequestDTO(username, password))
            }
        }
        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token?.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))

    }

    override suspend fun register(username: String, password: String) {
        val response: RegisterResponseDTO = requestHandler.request {
            post("auth/register") {
                setBody(RegisterRequestDTO(username, password))
            }
        }
        val accessToken = response.token?.accessToken ?: return
        val refreshToken = response.token?.refreshToken ?: return
        accessTokenStorage.saveAccessToken(AccessToken(accessToken))
        refreshTokenStorage.saveRefreshToken(RefreshToken(refreshToken))
    }
}