package com.cashew.features.parties.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.core.wrappers.wrap
import com.cashew.features.parties.createPartiesListComponent

class RealPartiesComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, PartiesComponent {
    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: CStateFlow<ChildStack<*, PartiesComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle).wrap()

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): PartiesComponent.Child = when (config) {
        ChildConfig.List -> PartiesComponent.Child.List(
            componentFactory.createPartiesListComponent(componentContext)
        )
    }

    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object List : ChildConfig
    }
}