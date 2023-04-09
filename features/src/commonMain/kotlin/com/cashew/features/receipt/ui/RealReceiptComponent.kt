package com.cashew.features.receipt.ui

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
import com.cashew.features.receipt.createReceiptListComponent
import com.cashew.features.receipt.ui.list.ReceiptListComponent

class RealReceiptComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, ReceiptComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: CStateFlow<ChildStack<*, ReceiptComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle).wrap()

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): ReceiptComponent.Child = when (config) {
        ChildConfig.List -> ReceiptComponent.Child.List(
            componentFactory.createReceiptListComponent(
                componentContext,
                ::onReceiptListOutput
            )
        )
    }

    private fun onReceiptListOutput(output: ReceiptListComponent.Output) {
        when (output) {
            else -> {}
        }
    }


    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object List : ChildConfig
    }

}