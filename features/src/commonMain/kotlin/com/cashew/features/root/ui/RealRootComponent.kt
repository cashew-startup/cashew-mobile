package com.cashew.features.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cashew.core.ComponentFactory
import com.cashew.core.createMessageComponent
import com.cashew.core.message.ui.MessageComponent
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.core.utils.toStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.core.wrappers.wrap
import com.cashew.features.authorization_flow.createAuthorizationFlowComponent
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent
import com.cashew.features.main.createMainComponent
import com.cashew.features.welcome.createWelcomeComponent
import com.cashew.features.welcome.ui.WelcomeComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    private val credentialsStorage: CredentialsStorage,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, RootComponent {

    private val coroutineScope = componentCoroutineScope()

    private val navigation = StackNavigation<ChildConfig>()

    override val childStackFlow: CStateFlow<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Welcome,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle).wrap()

    override val messageComponent: MessageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    init {
        coroutineScope.safeLaunch(exceptionHandler) {
            //if (credentialsStorage.getCredentials() != null) {
                navigation.navigate { listOf(ChildConfig.Main) }
            //}
        }
    }

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
            ChildConfig.Main -> RootComponent.Child.Main(
                componentFactory.createMainComponent(componentContext)
            )
        }
    }

    private fun onAuthorizationFlowOutput(output: AuthorizationFlowComponent.Output) {
        when (output) {
            AuthorizationFlowComponent.Output.OnAccountAvailable -> {
                navigation.replaceCurrent(ChildConfig.Main)
            }
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

        @Parcelize
        object Main : ChildConfig

    }

}