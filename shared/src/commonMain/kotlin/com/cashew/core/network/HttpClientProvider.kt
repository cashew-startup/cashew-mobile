package com.cashew.core.network

import com.cashew.core.authorization.AccessTokenProvider
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class HttpClientProvider(
    private val backendUrl: String,
    private val accessTokenProvider: AccessTokenProvider
) {
    val authorizedHttpClient = createHttpClient(isAuthorized = true)
    val unauthorizedHttpClient = createHttpClient(isAuthorized = false)

    private fun createHttpClient(isAuthorized: Boolean): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            install(Logging) { level = LogLevel.ALL }
            install(DefaultRequest) {
                url(backendUrl)
                if (isAuthorized) {
                    bearerAuth(accessTokenProvider.accessToken?.value ?: "")
                }
            }

        }
    }
}