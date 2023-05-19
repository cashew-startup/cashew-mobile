package com.cashew.features.receipt.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.core.wrappers.wrap
import com.cashew.features.receipt.createReceiptDetailsComponent
import com.cashew.features.receipt.createReceiptListComponent
import com.cashew.features.receipt.domain.ReceiptId
import com.cashew.features.receipt.ui.details.ReceiptDetailsComponent
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
        is ChildConfig.List -> ReceiptComponent.Child.List(
            componentFactory.createReceiptListComponent(
                componentContext,
                ::onReceiptListOutput
            )
        )
        is ChildConfig.Details -> ReceiptComponent.Child.Details(
            componentFactory.createReceiptDetailsComponent(
                componentContext,
                ReceiptId(config.receiptId),
                ::onReceiptDetailsOutput
            )
        )
    }

    private fun onReceiptDetailsOutput(output: ReceiptDetailsComponent.Output) {
        when (output) {
            is ReceiptDetailsComponent.Output.OnDismissRequested -> {
                navigation.pop()
            }
            is ReceiptDetailsComponent.Output.OnProductChosen -> {

            }
            is ReceiptDetailsComponent.Output.OnReceiptSaved -> {
                navigation.pop()
            }
        }
    }

    private fun onReceiptListOutput(output: ReceiptListComponent.Output) {
        when (output) {
            is ReceiptListComponent.Output.OnReceiptChosen -> {
                navigation.push(ChildConfig.Details(output.receiptId.value))
            }
        }
    }


    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object List : ChildConfig

        @Parcelize
        data class Details(val receiptId: Long) : ChildConfig
    }

}