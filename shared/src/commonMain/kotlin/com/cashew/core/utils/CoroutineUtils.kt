package com.cashew.core.utils

import com.cashew.core.network.exceptions.ExceptionHandler
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(
    exceptionHandler: ExceptionHandler,
    onExceptionHandled: (Exception) -> Unit = {},
    block: suspend () -> Unit,
) = launch {
    try {
        block()
    } catch (e: CancellationException) {
        // do nothing
    } catch (e: Exception) {
        exceptionHandler.handleException(e)
        onExceptionHandled(e)
    }
}

fun CoroutineScope.safeLaunch(
    exceptionHandler: (Exception) -> Unit,
    block: suspend () -> Unit
) = launch {
    try {
        block()
    } catch (e: CancellationException) {
        // do nothing
    } catch (e: Exception) {
        exceptionHandler(e)
    }
}