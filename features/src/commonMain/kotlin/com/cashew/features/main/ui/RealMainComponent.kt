package com.cashew.features.main.ui

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
import com.cashew.features.receipt.createReceiptComponent

class RealMainComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, MainComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: CStateFlow<ChildStack<*, MainComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Receipt,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle).wrap()

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): MainComponent.Child = when (config) {
        ChildConfig.Receipt -> {
            MainComponent.Child.Receipt(
                componentFactory.createReceiptComponent(componentContext)
            )
        }
    }


    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object Receipt : ChildConfig
    }
}