package com.cashew.features.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation

class RealRootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override lateinit var childStack: ChildStack<*, RootComponent.Child>

    sealed interface ChildConfig {

        object Empty : ChildConfig

    }

}