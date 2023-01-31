package com.cashew.core.network

import com.cashew.core.authorization.TokenProvider
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class AuthorizationInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor{

    override suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        request.bearerAuth(tokenProvider.token?.value ?: "")
        return sender.execute(request)
    }

}