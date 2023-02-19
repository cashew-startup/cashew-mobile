package com.cashew.features.main.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow

interface MainScreenComponent {
    val childStackFlow: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

    }
}