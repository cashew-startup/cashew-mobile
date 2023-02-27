package com.cashew.core.wrappers

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*


class DerivedStateFlow<T>(
    private val getValue: () -> T,
    private val flow: Flow<T>
) : StateFlow<T> {

    override val replayCache: List<T>
        get () = listOf(value)

    override val value: T
        get () = getValue()

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        coroutineScope { flow.distinctUntilChanged().stateIn(this).collect(collector) }
    }
}

fun <T1, R> StateFlow<T1>.map(transform: (T1) -> R): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(this.value) },
        flow = this.map { a -> transform(a) }
    )
}

fun <T1, T2, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    transform: (T1, T2) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(flow1.value, flow2.value) },
        flow = combine(flow1, flow2) { a, b -> transform(a, b) }
    )
}

fun <T1, T2, T3, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    transform: (T1, T2, T3) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(flow1.value, flow2.value, flow3.value) },
        flow = combine(flow1, flow2, flow3) { a, b, c -> transform(a, b, c) }
    )
}

fun <T1, T2, T3, T4, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    transform: (T1, T2, T3, T4) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(flow1.value, flow2.value, flow3.value, flow4.value) },
        flow = combine(flow1, flow2, flow3, flow4) { a, b, c, d -> transform(a, b, c, d) }
    )
}

fun <T1, T2, T3, T4, T5, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    transform: (T1, T2, T3, T4, T5) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(flow1.value, flow2.value, flow3.value, flow4.value, flow5.value) },
        flow = combine(flow1, flow2, flow3, flow4, flow5) { a, b, c, d, e -> transform(a, b, c, d, e) }
    )
}

// and so on