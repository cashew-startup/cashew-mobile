package com.cashew.core.network

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

interface Interceptor {

    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall

}