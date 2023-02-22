package com.cashew.core.network

import com.cashew.core.network.authorization.TokenRefresher
import com.cashew.core.network.exceptions.ExceptionMapper
import com.cashew.core.storage.providers.AccessTokenProvider
import com.cashew.core.storage.providers.RefreshTokenProvider
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
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
            install(ContentNegotiation) { json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                    encodeDefaults = true
                    prettyPrint = true
                }
            ) }
            install(Logging) { level = LogLevel.ALL }
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
                            BearerTokens(accessToken, refreshToken)
                        }

                        refreshTokens {
                            tokenRefresher.refreshTokens(client)
                            val accessToken = accessTokenProvider.getAccessToken()?.value
                                ?: return@refreshTokens null
                            val refreshToken = refreshTokenProvider.getRefreshToken()?.value
                                ?: return@refreshTokens null
                            BearerTokens(accessToken, refreshToken)
                        }
                    }
                }
            }
            expectSuccess = true
            install(HttpCallValidator) {
                handleResponseExceptionWithRequest { cause, request ->
                    if (cause is Exception) throw exceptionMapper.mapException(cause)
                    else throw cause
                }
            }
        }
    }
}