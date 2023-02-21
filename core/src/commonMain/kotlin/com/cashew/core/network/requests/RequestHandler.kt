package com.cashew.core.network.requests

import com.cashew.core.network.HttpClientProvider
import com.cashew.core.network.exceptions.ExceptionMapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.statement.*

class RequestHandler(
    val httpClientProvider: HttpClientProvider,
    val exceptionMapper: ExceptionMapper
) {

    suspend inline fun <reified T> request(
        requireAuthorized: Boolean = false,
        block: HttpClient.() -> HttpResponse
    ): T = try {
        val client = if (requireAuthorized) httpClientProvider.authorizedHttpClient
        else httpClientProvider.unauthorizedHttpClient
        client.block().body()
    } catch (e: Exception) {
        throw exceptionMapper.mapException(e)
    }

}