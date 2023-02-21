package com.cashew.features.authorization_flow.ui.register

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.network.exceptions.ClientRequestException
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import kotlinx.coroutines.flow.MutableStateFlow

private const val HTTP_CONFLICT_CODE = 409

class RealAuthorizationRegisterComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationRegisterComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, AuthorizationRegisterComponent {

    private val coroutineScope = componentCoroutineScope()

    override val usernameState: MutableStateFlow<String> = MutableStateFlow("")
    override val passwordState: MutableStateFlow<String> = MutableStateFlow("")
    override val confirmPasswordState: MutableStateFlow<String> = MutableStateFlow("")
    override val isUsernameErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isConfirmPasswordState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val errorsState: MutableStateFlow<List<AuthorizationRegisterComponent.Error>> =
        MutableStateFlow(emptyList())

    override fun onCreateClick() {
        if (!validateCredentials()) return
        coroutineScope.safeLaunch(
            exceptionHandler,
            onExceptionHandled = {
                if (it !is ClientRequestException) return@safeLaunch
                if (it.code == HTTP_CONFLICT_CODE) {
                    errorsState.value = listOf(AuthorizationRegisterComponent.Error.UserAlreadyExists)
                }
            }
        ) {
            authorizationRepository.register(
                username = usernameState.value,
                password = passwordState.value
            )
            onOutput(AuthorizationRegisterComponent.Output.OnAccountCreated)
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
        if (usernameState.value.length !in USERNAME_MIN_CHAR_LIMIT..USERNAME_MAX_CHAR_LIMIT) {
            errors += AuthorizationRegisterComponent.Error.ShortUsername
        }
        if (passwordState.value.length !in PASSWORD_MIN_CHAR_LIMIT..PASSWORD_MAX_CHAR_LIMIT) {
            errors += AuthorizationRegisterComponent.Error.ShortPassword
        }
        if (passwordState.value != confirmPasswordState.value) {
            errors += AuthorizationRegisterComponent.Error.PasswordsNotMatch
        }
        errorsState.value = errors
        return errors.isEmpty()
    }

    private companion object {
        const val USERNAME_MIN_CHAR_LIMIT = 4
        const val USERNAME_MAX_CHAR_LIMIT = 25
        const val PASSWORD_MIN_CHAR_LIMIT = 8
        const val PASSWORD_MAX_CHAR_LIMIT = 25
    }
}