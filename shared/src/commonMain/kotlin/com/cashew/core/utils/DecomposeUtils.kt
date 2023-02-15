package com.cashew.core.utils

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

fun <T : Any> Value<T>.toStateFlow(lifecycle: Lifecycle): StateFlow<T> {
    val stateFlow = MutableStateFlow(this.value)
    if (lifecycle.state != Lifecycle.State.DESTROYED) {
        val observer = { value: T -> stateFlow.value = value }
        subscribe(observer)
        lifecycle.doOnDestroy {
            unsubscribe(observer)
        }
    }
    return stateFlow
}

fun Lifecycle.coroutineScope(
    coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate
): CoroutineScope {
    val scope = CoroutineScope(coroutineContext)
    doOnDestroy(scope::cancel)
    return scope
}

fun Lifecycle.coroutineScope(
    job: Job = SupervisorJob(),
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
): CoroutineScope {
    return coroutineScope(job + coroutineDispatcher)
}

fun LifecycleOwner.componentCoroutineScope(
    coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate
): CoroutineScope {
    return lifecycle.coroutineScope(coroutineContext)
}

fun LifecycleOwner.componentCoroutineScope(
    job: Job = SupervisorJob(),
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
): CoroutineScope {
    return lifecycle.coroutineScope(job, coroutineDispatcher)
}