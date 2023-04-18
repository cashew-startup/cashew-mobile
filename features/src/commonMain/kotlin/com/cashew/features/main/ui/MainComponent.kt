package com.cashew.features.main.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.profile.ui.ProfileComponent

interface MainComponent {

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Profile(val component: ProfileComponent) : Child
    }
}