package com.cashew.core.utils

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.cashew.core.wrappers.CStateFlow
import com.cashew.core.wrappers.wrap
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.decompose.observe
import me.aartikov.replica.single.Loadable
import me.aartikov.replica.single.Replica

fun <T : Any> Replica<T>.observe(
    lifecycle: Lifecycle
): CStateFlow<Loadable<T>> {
    return observe(lifecycle).stateFlow.wrap()
}