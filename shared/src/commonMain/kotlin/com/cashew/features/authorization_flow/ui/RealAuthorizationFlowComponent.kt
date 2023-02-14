package com.cashew.features.authorization_flow.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.utils.toStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealAuthorizationFlowComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationFlowComponent.Output) -> Unit
) : ComponentContext by componentContext, AuthorizationFlowComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: StateFlow<ChildStack<*, AuthorizationFlowComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Login,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        childConfig: ChildConfig,
        componentContext: ComponentContext
    ): AuthorizationFlowComponent.Child = when (childConfig) {
        ChildConfig.Login -> TODO()
        ChildConfig.Register -> TODO()
    }


    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Register : ChildConfig

        @Parcelize
        object Login : ChildConfig

    }
}