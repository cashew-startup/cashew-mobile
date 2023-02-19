package com.cashew.core.network

import com.cashew.core.network.authorization.TokenRefresher
import com.cashew.core.network.authorization.providers.AccessTokenProvider
import com.cashew.core.network.authorization.providers.RefreshTokenProvider
import com.cashew.core.network.exceptions.ExceptionMapper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

class HttpClientProvider(
    private val backendUrl: String,
    private val accessTokenProvider: AccessTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider,
    private val tokenRefresher: TokenRefresher
) {
    val authorizedHttpClient = createHttpClient(isAuthorized = true)
    val unauthorizedHttpClient = createHttpClient(isAuthorized = false)

    private fun createHttpClient(isAuthorized: Boolean): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            install(Logging) { level = LogLevel.ALL }
            install(DefaultRequest) {
                url(backendUrl)
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
        }
    }
}