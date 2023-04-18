package com.cashew.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.message.ui.MessageComponent
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent
import com.cashew.features.main.ui.MainComponent
import com.cashew.features.welcome.ui.WelcomeComponent

interface RootComponent {

    val messageComponent: MessageComponent

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Welcome(val component: WelcomeComponent) : Child

        class AuthorizationFlow(val component: AuthorizationFlowComponent) : Child

        class Main(val component: MainComponent) : Child
    }

}