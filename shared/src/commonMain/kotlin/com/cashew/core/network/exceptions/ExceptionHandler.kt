package com.cashew.core.network.exceptions

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ExceptionHandler(
    private val exceptionMapper: ExceptionMapper
) {

    private val exceptionChannel: Channel<AppException> = Channel(Channel.UNLIMITED)
    val exceptionFlow = exceptionChannel.receiveAsFlow()

    fun handleException(exception: AppException) {
        exceptionChannel.trySend(exception)
    }

    fun handleException(exception: Exception) {
        handleException(exceptionMapper.mapException(exception))
    }
}