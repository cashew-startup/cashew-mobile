package com.cashew.core.utils

import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.decompose.observe
import me.aartikov.replica.single.Loadable
import me.aartikov.replica.single.Replica

fun <T : Any> Replica<T>.observe(
    lifecycle: Lifecycle
): StateFlow<Loadable<T>> {
    return observe(lifecycle).stateFlow
}