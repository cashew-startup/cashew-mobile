package com.cashew.features.authorization_flow.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent
import com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthorizationFlowComponent {

    val childStackFlow: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Register(val component: AuthorizationRegisterComponent) : Child
        class Login(val component: AuthorizationLoginComponent) : Child
    }

    sealed interface Output {
        object OnAccountAvailable : Output
    }

}