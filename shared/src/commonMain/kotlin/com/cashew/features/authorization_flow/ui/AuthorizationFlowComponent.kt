package com.cashew.features.authorization_flow.ui

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow

interface AuthorizationFlowComponent {

    val childStackFlow: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        object Register : Child
        object Login : Child
    }

    sealed interface Output {

    }

}