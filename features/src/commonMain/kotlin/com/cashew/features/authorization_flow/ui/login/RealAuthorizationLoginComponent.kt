package com.cashew.features.authorization_flow.ui.login

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.features.authorization_flow.data.AuthorizationRepository

class RealAuthorizationLoginComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationLoginComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, AuthorizationLoginComponent {

    private val coroutineScope = componentCoroutineScope()

    override val usernameState: CMutableStateFlow<String> = CMutableStateFlow("")
    override val passwordState: CMutableStateFlow<String> = CMutableStateFlow("")

    override val isUsernameErrorState: CMutableStateFlow<Boolean> = CMutableStateFlow(false)
    override val isPasswordErrorState: CMutableStateFlow<Boolean> = CMutableStateFlow(false)
    override val errorsState: CMutableStateFlow<List<AuthorizationLoginComponent.Error>> =
        CMutableStateFlow(emptyList())

    override fun onLoginClick() {
        coroutineScope.safeLaunch(exceptionHandler) {
            val result = authorizationRepository.login(
                username = usernameState.value,
                password = passwordState.value
            )
            when (result) {
                AuthorizationRepository.LoginResult.InvalidCredentials -> {
                    errorsState.value = listOf(AuthorizationLoginComponent.Error.InvalidCredentials)
                }
                AuthorizationRepository.LoginResult.Ok -> {
                    onOutput(AuthorizationLoginComponent.Output.OnLoggedIn)
                }
            }
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