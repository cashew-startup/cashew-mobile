package com.cashew.core.network.exceptions

abstract class AppException(message: String?, cause: Throwable?) : Exception(message, cause)

class UnauthorizedException(cause: Throwable?) : AppException("Server returned 403 Unauthorized", cause)

class NoInternetException(cause: Throwable?) : AppException("No internet!", cause)

class NoResponseException(cause: Throwable?) : AppException("No response from the server!", cause)

class SerializationException(cause: Throwable?) : AppException("Invalid data for serialization!", cause)

class ClientRequestException(cause: Throwable?) : AppException("Request error", cause)

class ServerResponseException(cause: Throwable?) : AppException("Server response error, bad response", cause)

class UnknownException(message: String? = null, cause: Throwable?) : AppException(message, cause)

