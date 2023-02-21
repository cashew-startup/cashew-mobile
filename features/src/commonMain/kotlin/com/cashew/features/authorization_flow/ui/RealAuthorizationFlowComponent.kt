package com.cashew.features.authorization_flow.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.utils.toStateFlow
import com.cashew.features.authorization_flow.createAuthorizationLoginComponent
import com.cashew.features.authorization_flow.createAuthorizationRegisterComponent
import com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent
import com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent
import kotlinx.coroutines.flow.StateFlow

class RealAuthorizationFlowComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationFlowComponent.Output) -> Unit,
    private val componentFactory: ComponentFactory
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
        ChildConfig.Login -> AuthorizationFlowComponent.Child.Login(
            componentFactory.createAuthorizationLoginComponent(
                componentContext,
                ::onAuthorizationLoginOutput
            )
        )

        ChildConfig.Register -> AuthorizationFlowComponent.Child.Register(
            componentFactory.createAuthorizationRegisterComponent(
                componentContext,
                ::onAuthorizationRegisterOutput
            )
        )
    }

    private fun onAuthorizationRegisterOutput(output: AuthorizationRegisterComponent.Output) {
        when (output) {
            AuthorizationRegisterComponent.Output.OnAccountCreated -> {
                onOutput(AuthorizationFlowComponent.Output.OnAccountAvailable)
            }
            AuthorizationRegisterComponent.Output.OnBackPressed -> navigation.pop()
        }
    }

    private fun onAuthorizationLoginOutput(output: AuthorizationLoginComponent.Output) {
        when (output) {
            AuthorizationLoginComponent.Output.OnCreateNewAccountRequested -> navigation.push(
                ChildConfig.Register
            )
            AuthorizationLoginComponent.Output.OnLoggedIn -> {
                onOutput(AuthorizationFlowComponent.Output.OnAccountAvailable)
            }
        }
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Register : ChildConfig

        @Parcelize
        object Login : ChildConfig

    }
}