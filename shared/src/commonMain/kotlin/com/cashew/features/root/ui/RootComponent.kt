package com.cashew.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.features.welcome.ui.WelcomeComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStackFlow: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Welcome(val component: WelcomeComponent) : Child
    }

}