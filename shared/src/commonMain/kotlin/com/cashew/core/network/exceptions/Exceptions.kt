package com.cashew.core.network.exceptions

sealed class AppException(
    message: String?,
    cause: Throwable?
) : Exception(message, cause)

class UnauthorizedException(
    cause: Throwable?
) : AppException("Server returned 401 Unauthorized", cause)

class NoInternetException(
    cause: Throwable?
) : AppException("No internet!", cause)

class NoResponseException(
    code: Int?,
    cause: Throwable?
) : AppException("No response from the server!", cause)

class SerializationException(cause: Throwable?) : AppException("Invalid data for serialization!", cause)

class ClientRequestException(
    code: Int,
    cause: Throwable?
) : AppException("Request error", cause)

class ServerResponseException(
    code: Int,
    cause: Throwable?
) : AppException("Server response error, bad response", cause)

class UnknownException(
    cause: Throwable?,
    message: String? = cause?.message
) : AppException(message, cause)

