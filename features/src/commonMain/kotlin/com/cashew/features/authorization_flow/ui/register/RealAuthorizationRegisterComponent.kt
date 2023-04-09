package com.cashew.features.authorization_flow.ui.register

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.features.authorization_flow.data.AuthorizationRepository

class RealAuthorizationRegisterComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationRegisterComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, AuthorizationRegisterComponent {

    private val coroutineScope = componentCoroutineScope()

    override val usernameState: CMutableStateFlow<String> = CMutableStateFlow("")
    override val passwordState: CMutableStateFlow<String> = CMutableStateFlow("")
    override val confirmPasswordState: CMutableStateFlow<String> = CMutableStateFlow("")
    override val isUsernameErrorState: CMutableStateFlow<Boolean> = CMutableStateFlow(false)
    override val isPasswordErrorState: CMutableStateFlow<Boolean> = CMutableStateFlow(false)
    override val isConfirmPasswordState: CMutableStateFlow<Boolean> = CMutableStateFlow(false)
    override val errorsState: CMutableStateFlow<List<AuthorizationRegisterComponent.Error>> =
        CMutableStateFlow(emptyList())

    override fun onCreateClick() {
        if (!validateCredentials()) return
        coroutineScope.safeLaunch(exceptionHandler) {
            val result = authorizationRepository.register(
                username = usernameState.value,
                password = passwordState.value
            )
            when (result) {
                AuthorizationRepository.RegisterResult.UserAlreadyExists -> {
                    errorsState.value = listOf(AuthorizationRegisterComponent.Error.UserAlreadyExists)
                }
                AuthorizationRepository.RegisterResult.Ok -> {
                    onOutput(AuthorizationRegisterComponent.Output.OnAccountCreated)
                }
            }
        }
    }

    override fun onBackPressed() {
        onOutput(AuthorizationRegisterComponent.Output.OnBackPressed)
    }

    override fun onUsernameTextChanged(changedUsername: String) {
        usernameState.value = changedUsername
    }

    override fun onPasswordTextChanged(changedPassword: String) {
        passwordState.value = changedPassword
    }

    override fun onConfirmPasswordChanged(changedConfirmPassword: String) {
        confirmPasswordState.value = changedConfirmPassword
    }

    private fun validateCredentials(): Boolean {
        val errors = mutableListOf<AuthorizationRegisterComponent.Error>()
        if (usernameState.value.length !in USERNAME_LENGTH_MIN..USERNAME_LENGTH_MAX) {
            errors += AuthorizationRegisterComponent.Error.ShortUsername
        }
        if (passwordState.value.length !in PASSWORD_LENGTH_MIN..PASSWORD_LENGTH_MAX) {
            errors += AuthorizationRegisterComponent.Error.ShortPassword
        }
        if (passwordState.value != confirmPasswordState.value) {
            errors += AuthorizationRegisterComponent.Error.PasswordsNotMatch
        }
        errorsState.value = errors
        return errors.isEmpty()
    }

    private companion object {
        const val USERNAME_LENGTH_MIN = 4
        const val USERNAME_LENGTH_MAX = 25
        const val PASSWORD_LENGTH_MIN = 8
        const val PASSWORD_LENGTH_MAX = 25
    }
}