package com.cashew.features.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.features.authorization_flow.createAuthorizationFlowComponent
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent
import com.cashew.features.welcome.createWelcomeComponent
import com.cashew.features.welcome.ui.WelcomeComponent
import kotlinx.coroutines.flow.StateFlow

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

            ChildConfig.AuthorizationFlow -> RootComponent.Child.AuthorizationFlow(
                componentFactory.createAuthorizationFlowComponent(
                    componentContext,
                    ::onAuthorizationFlowOutput
                )
            )
        }
    }

    private fun onAuthorizationFlowOutput(output: AuthorizationFlowComponent.Output) {
        when (output) {
            AuthorizationFlowComponent.Output.OnAccountAvailable -> {}
        }
    }

    private fun onWelcomeScreenOutput(output: WelcomeComponent.Output) {
        when (output) {
            WelcomeComponent.Output.OnGetStartedRequested -> navigation.push(ChildConfig.AuthorizationFlow)
        }
    }


    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Welcome : ChildConfig

        @Parcelize
        object AuthorizationFlow : ChildConfig

    }

}