package com.cashew.core.network

import com.cashew.core.authorization.AccessTokenProvider
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class AuthorizationInterceptor(
    private val accessTokenProvider: AccessTokenProvider
) : Interceptor{

    override suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        request.bearerAuth(accessTokenProvider.accessToken?.value ?: "")
        return sender.execute(request)
    }

}