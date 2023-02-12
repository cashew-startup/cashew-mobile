package com.cashew.features.root.ui

import android.provider.DocumentsContract.Root
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.features.welcome.createWelcomeComponent
import com.cashew.features.welcome.ui.WelcomeComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: StateFlow<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Welcome,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        childConfig: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (childConfig) {
            ChildConfig.Welcome -> RootComponent.Child.Welcome(
                componentFactory.createWelcomeComponent(componentContext, ::onWelcomeScreenOutput)
            )
        }
    }

    private fun onWelcomeScreenOutput(output: WelcomeComponent.Output) {
        when (output) {
            WelcomeComponent.Output.OnGetStartedRequested -> {}
        }
    }


    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Welcome : ChildConfig

    }

}