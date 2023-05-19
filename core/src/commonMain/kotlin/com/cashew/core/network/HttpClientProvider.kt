package com.cashew.core.network

import com.cashew.core.authorization.TokenRefresher
import com.cashew.core.network.exceptions.ExceptionMapper
import com.cashew.core.storage.providers.AccessTokenProvider
import com.cashew.core.storage.providers.RefreshTokenProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val backendUrl: String,
    private val accessTokenProvider: AccessTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider,
    private val tokenRefresher: TokenRefresher,
    private val exceptionMapper: ExceptionMapper
) {
    val authorizedHttpClient = createHttpClient(isAuthorized = true)
    val unauthorizedHttpClient = createHttpClient(isAuthorized = false)

    @OptIn(ExperimentalSerializationApi::class)
    private fun createHttpClient(isAuthorized: Boolean): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                    encodeDefaults = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url {
                    host = backendUrl
                }
                contentType(ContentType.Application.Json)
            }

            if (isAuthorized) {
                install(Auth) {
                    bearer {
                        loadTokens {
                            val accessToken = accessTokenProvider.getAccessToken()?.value
                                ?: return@loadTokens null
                            val refreshToken = refreshTokenProvider.getRefreshToken()?.value
                                ?: return@loadTokens null
                            println("Load tokens: accessToken = $accessToken refreshToken = $refreshToken")
                            BearerTokens(accessToken, refreshToken)
                        }

                        refreshTokens {
                            tokenRefresher.refreshTokens(client)
                            val accessToken = accessTokenProvider.getAccessToken()?.value
                                ?: return@refreshTokens null
                            val refreshToken = refreshTokenProvider.getRefreshToken()?.value
                                ?: return@refreshTokens null
                            println("Refresh tokens: accessToken = $accessToken refreshToken = $refreshToken")
                            BearerTokens(accessToken, refreshToken)
                        }
                    }
                }
            }
            expectSuccess = true
            install(HttpCallValidator) {
                handleResponseExceptionWithRequest { cause, request ->
                    cause.printStackTrace()
                    when (cause) {
                        is ResponseException -> {
                            val exception = try {
                                exceptionMapper.mapStatusDtoToException(cause.response.body())
                                    ?: exceptionMapper.mapResponseException(cause)
                            } catch (e: SerializationException) {
                                exceptionMapper.mapResponseException(cause)
                            }
                            throw exception
                        }
                        is Exception -> throw exceptionMapper.mapException(cause)
                        else -> throw cause
                    }
                }
            }
        }
    }
}