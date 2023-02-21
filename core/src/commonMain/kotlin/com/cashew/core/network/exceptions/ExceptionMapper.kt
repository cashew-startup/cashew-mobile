package com.cashew.core.network.exceptions

import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

import kotlinx.serialization.SerializationException as KotlinXSerializationException

class ExceptionMapper {

    fun mapResponseException(exception: ResponseException): AppException {
        return when (val code = exception.response.status.value) {
            in 300..399 -> ServerResponseException(code, exception)
            HttpStatusCode.Unauthorized.value -> UnauthorizedException(exception)
            HttpStatusCode.GatewayTimeout.value,
            HttpStatusCode.RequestTimeout.value,
            HttpStatusCode.ServiceUnavailable.value -> NoResponseException(code, exception)
            in 400..499 -> ClientRequestException(code, exception)
            in 500..599 -> ServerResponseException(code, exception)
            else -> UnknownException(exception)
        }
    }

    fun mapException(exception: Exception): AppException {
        return when (exception) {
            is AppException -> exception
            is ResponseException -> mapResponseException(exception)
            is ConnectTimeoutException -> NoResponseException(null, exception)
            is IOException -> NoInternetException(exception)
            is KotlinXSerializationException -> SerializationException(exception)
            else -> UnknownException(exception)
        }
    }
}