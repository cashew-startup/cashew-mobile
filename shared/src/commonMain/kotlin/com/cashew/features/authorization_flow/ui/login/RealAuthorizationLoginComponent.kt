package com.cashew.features.authorization_flow.ui.login

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.DefaultNetworkCoroutineContext
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import com.cashew.features.authorization_flow.domain.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealAuthorizationLoginComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationLoginComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository
) : ComponentContext by componentContext, AuthorizationLoginComponent {

    private val coroutineScope = componentCoroutineScope(DefaultNetworkCoroutineContext)

    override val usernameState: MutableStateFlow<String> = MutableStateFlow("")
    override val passwordState: MutableStateFlow<String> = MutableStateFlow("")

    override val isUsernameErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val errorsState: MutableStateFlow<List<AuthorizationLoginComponent.Error>> =
        MutableStateFlow(emptyList())

    override fun onLoginClick() {
        coroutineScope.launch {
            val result = authorizationRepository.login(
                username = usernameState.value,
                password = passwordState.value
            )

            if (result is LoginResult.Success) {
                onOutput(AuthorizationLoginComponent.Output.OnLoggedIn)
            } else errorsState.value = listOf(AuthorizationLoginComponent.Error.InvalidCredentials)
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