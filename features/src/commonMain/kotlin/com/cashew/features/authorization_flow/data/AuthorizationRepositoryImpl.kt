package com.cashew.features.authorization_flow.data

import com.cashew.core.authorization.*
import com.cashew.core.network.dto.LoginRequestDTO
import com.cashew.core.network.dto.LoginResponseDTO
import com.cashew.core.network.dto.RegisterRequestDTO
import com.cashew.core.network.dto.RegisterResponseDTO
import com.cashew.core.network.exceptions.ClientRequestException
import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.AccessTokenStorage
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.core.storage.storages.RefreshTokenStorage
import com.cashew.features.authorization_flow.data.AuthorizationRepository.LoginResult
import com.cashew.features.authorization_flow.data.AuthorizationRepository.RegisterResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthorizationRepositoryImpl(
    private val httpClient: HttpClient,
    private val credentialsStorage: CredentialsStorage,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage
) : AuthorizationRepository {

    override suspend fun login(username: String, password: String): LoginResult = try {
        httpClient
            .post("auth/login") {
                setBody(LoginRequestDTO(username, password))
            }
            .body<LoginResponseDTO>()
            .also {
                credentialsStorage.saveCredentials(
                    Credentials(
                        userId = UserId(it.id),
                        username = Username(username),
                        password = Password(password)
                    )
                )
            }
            .token
            .let {
                accessTokenStorage.saveAccessToken(AccessToken(it.accessToken))
                refreshTokenStorage.saveRefreshToken(RefreshToken(it.refreshToken))
                LoginResult.Ok
            }
    } catch (e: UnauthorizedException) {
        LoginResult.InvalidCredentials
    }

    override suspend fun register(username: String, password: String): RegisterResult = try {
        httpClient
            .post("auth/register") {
                setBody(RegisterRequestDTO(username, password))
            }
            .body<RegisterResponseDTO>()
            .also {
                credentialsStorage.saveCredentials(
                    Credentials(
                        userId = UserId(it.id),
                        username = Username(username),
                        password = Password(password)
                    )
                )
            }
            .token
            .let {
                accessTokenStorage.saveAccessToken(AccessToken(it.accessToken))
                refreshTokenStorage.saveRefreshToken(RefreshToken(it.refreshToken))
                RegisterResult.Ok
            }
    } catch (e: ClientRequestException) {
        if (e.code != HttpStatusCode.Conflict.value) throw e
        RegisterResult.UserAlreadyExists
    }
}