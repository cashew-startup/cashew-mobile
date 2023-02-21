package com.cashew.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*

fun HttpSend.addInterceptor(interceptor: Interceptor) {
    intercept { request -> interceptor.intercept(this, request) }
}

fun HttpSend.addInterceptors(interceptors: List<Interceptor>) {
    interceptors.forEach { addInterceptor(it) }
}

fun HttpClient.addInterceptor(interceptor: Interceptor) {
    plugin(HttpSend).addInterceptor(interceptor)
}

fun HttpClient.addInterceptors(interceptors: List<Interceptor>) {
    plugin(HttpSend).addInterceptors(interceptors)
}

