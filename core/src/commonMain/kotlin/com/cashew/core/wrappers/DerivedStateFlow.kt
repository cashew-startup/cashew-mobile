package com.cashew.core.wrappers

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*


private class DerivedStateFlow<in T, R>(
    private val resultingFlow: Flow<R>,
    private val flows: List<StateFlow<T>>,
    private val transform: (List<T>) -> R,
) : StateFlow<R> {

    override val replayCache: List<R>
        get() = listOf(value)


    private var previousStateFlowValues: List<T> = flows.map { it.value }
    private var previousValue: R = transform(previousStateFlowValues)

    override val value: R
        get() {
            val currentStateFlowValues = flows.map { it.value }
            if (currentStateFlowValues != previousStateFlowValues) {
                previousValue = transform(currentStateFlowValues)
                previousStateFlowValues = currentStateFlowValues
            }
            return previousValue
        }

    override suspend fun collect(collector: FlowCollector<R>): Nothing {
        coroutineScope { resultingFlow.distinctUntilChanged().stateIn(this).collect(collector) }
    }
}

fun <T1, R> StateFlow<T1>.map(transform: (T1) -> R): StateFlow<R> {
    return DerivedStateFlow(
        resultingFlow = this.map(transform),
        flows = listOf(this),
        transform = { transform(it[0]) }
    )
}

fun <T1, T2, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    transform: (T1, T2) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        resultingFlow = combine(
            flow1, flow2
        ) { a, b ->
            transform(a, b)
          },
        flows = listOf(flow1, flow2),
        transform = {
            transform(
                it[0] as T1,
                it[1] as T2
            )
        }
    )
}

fun <T1, T2, T3, R> derivedStateFlowOf(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    transform: (T1, T2, T3) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        resultingFlow = combine(
            flow1,
            flow2,
            flow3
        ) { a, b, c ->
            transform(a, b, c)
          },
        flows = listOf(flow1, flow2, flow3),
        transform = {
            transform(
                it[0] as T1,
                it[1] as T2,
                it[2] as T3
            )
        }
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
        resultingFlow = combine(
            flow1, flow2, flow3, flow4
        ) { a, b, c, d ->
            transform(a, b, c, d)
        },
        flows = listOf(flow1, flow2, flow3, flow4),
        transform = {
            transform(
                it[0] as T1,
                it[1] as T2,
                it[2] as T3,
                it[3] as T4
            )
        }
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
        resultingFlow = combine(
            flow1, flow2, flow3, flow4, flow5
        ) { a, b, c, d, e ->
            transform(a, b, c, d, e)
        },
        flows = listOf(flow1, flow2, flow3, flow4, flow5),
        transform = {
            transform(
                it[0] as T1,
                it[1] as T2,
                it[2] as T3,
                it[3] as T4,
                it[4] as T5
            )
        }
    )
}

// and so on