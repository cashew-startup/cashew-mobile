package com.cashew.core.network

import com.cashew.core.authorization.TokenProvider
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

class HttpClientProvider(
    private val backendUrl: String,
    private val tokenProvider: TokenProvider
) {

    fun createHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            install(Logging) { level = LogLevel.ALL }
        }.apply {
            addInterceptors(
                listOf(
                    AuthorizationInterceptor(tokenProvider)
                )
            )
        }
    }

}