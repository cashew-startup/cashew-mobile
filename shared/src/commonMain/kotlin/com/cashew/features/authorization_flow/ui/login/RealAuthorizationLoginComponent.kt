package com.cashew.features.authorization_flow.ui.login

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.DefaultNetworkCoroutineContext
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RealAuthorizationLoginComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationLoginComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository
) : ComponentContext by componentContext, AuthorizationLoginComponent {

    private val coroutineScope = componentCoroutineScope(DefaultNetworkCoroutineContext)

    override val usernameState: MutableStateFlow<String> = MutableStateFlow("")
    override val passwordState: MutableStateFlow<String> = MutableStateFlow("")

    override fun onLoginClick() {
        coroutineScope.launch {
            authorizationRepository.login(
                username = usernameState.value,
                password = passwordState.value
            )
            onOutput(AuthorizationLoginComponent.Output.OnLoggedIn)
        }
    }

    override fun onCreateNewAccountClick() {
        onOutput(AuthorizationLoginComponent.Output.OnCreateNewAccountRequested)
    }

    override fun onUsernameTextChanged(changedUsername: String) {
        usernameState.value = changedUsername
    }

    override fun onPasswordTextChanged(changedPassword: String) {
        passwordState.value = changedPassword
    }
}