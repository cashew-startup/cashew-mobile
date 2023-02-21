package com.cashew.core.utils

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.decompose.coroutineScope
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

fun LifecycleOwner.componentCoroutineScope(): CoroutineScope {
    return lifecycle.coroutineScope()
}