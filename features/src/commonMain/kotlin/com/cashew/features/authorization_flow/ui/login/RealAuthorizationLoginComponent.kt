package com.cashew.features.authorization_flow.ui.login

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import kotlinx.coroutines.flow.MutableStateFlow

class RealAuthorizationLoginComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationLoginComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, AuthorizationLoginComponent {

    private val coroutineScope = componentCoroutineScope()

    override val usernameState: MutableStateFlow<String> = MutableStateFlow("")
    override val passwordState: MutableStateFlow<String> = MutableStateFlow("")

    override val isUsernameErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val errorsState: MutableStateFlow<List<AuthorizationLoginComponent.Error>> =
        MutableStateFlow(emptyList())

    override fun onLoginClick() {
        coroutineScope.safeLaunch(
            exceptionHandler,
            onExceptionHandled = {
                if (it !is UnauthorizedException) return@safeLaunch
                errorsState.value = listOf(AuthorizationLoginComponent.Error.InvalidCredentials)
            }
        ) {
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