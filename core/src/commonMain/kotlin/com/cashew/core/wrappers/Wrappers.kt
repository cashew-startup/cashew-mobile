package com.cashew.core.wrappers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

interface Cancellable {
    fun cancel()
}

open class CFlow<T>(origin: Flow<T>) : Flow<T> by origin {

    fun collect(collector: (T) -> Unit): Cancellable {
        val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
        onEach {
            collector(it)
        }.launchIn(coroutineScope)

        return object : Cancellable {
            override fun cancel() = coroutineScope.cancel()
        }
    }
}

fun <T> Flow<T>.wrap() = CFlow(this)

open class CStateFlow<T>(
    private val origin: StateFlow<T>
) : CFlow<T>(origin), StateFlow<T> by origin {

    override suspend fun collect(collector: FlowCollector<T>) = origin.collect(collector)

}

fun <T> StateFlow<T>.wrap() = CStateFlow(this)

class CMutableStateFlow<T>(
    private val origin: MutableStateFlow<T>
) : CStateFlow<T>(origin), MutableStateFlow<T> by origin {

    constructor(initialValue: T) : this(MutableStateFlow(initialValue))

    override suspend fun collect(collector: FlowCollector<T>) = origin.collect(collector)
    override val replayCache: List<T> by origin::replayCache
    override var value: T by origin::value
}

fun <T> MutableStateFlow<T>.wrap() = CMutableStateFlow(this)


