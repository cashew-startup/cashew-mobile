package com.cashew.features.main.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.core.wrappers.wrap
import com.cashew.features.profile.createProfileComponent
import com.cashew.features.receipt.createReceiptComponent

class RealMainComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, MainComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: CStateFlow<ChildStack<*, MainComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Profile,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle).wrap()

    override val activeTabState: CMutableStateFlow<MainComponent.Tab> =
        CMutableStateFlow(MainComponent.Tab.Profile)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): MainComponent.Child = when (config) {
        ChildConfig.Profile -> MainComponent.Child.Profile(
            componentFactory.createProfileComponent(componentContext)
        )

        ChildConfig.Receipt -> MainComponent.Child.Receipt(
            componentFactory.createReceiptComponent(componentContext)
        )
    }

    override fun onActiveTabChanged(tab: MainComponent.Tab) {
        if (activeTabState.value == tab) return
        activeTabState.value = tab
        navigation.bringToFront(tab.toChildConfig())
    }

    private fun MainComponent.Tab.toChildConfig() = when (this) {
        MainComponent.Tab.Profile -> ChildConfig.Profile
        MainComponent.Tab.Receipt -> ChildConfig.Receipt
        else -> ChildConfig.Profile
    }

    private fun ChildConfig.toTab() = when (this) {
        ChildConfig.Profile -> MainComponent.Tab.Profile
        ChildConfig.Receipt -> MainComponent.Tab.Receipt
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Profile : ChildConfig

        @Parcelize
        object Receipt : ChildConfig
    }
}