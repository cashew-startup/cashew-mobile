package com.cashew.features.authorization_flow.ui.register

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.DefaultNetworkCoroutineContext
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import com.cashew.features.authorization_flow.domain.RegisterResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealAuthorizationRegisterComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthorizationRegisterComponent.Output) -> Unit,
    private val authorizationRepository: AuthorizationRepository
) : ComponentContext by componentContext, AuthorizationRegisterComponent {

    private val coroutineScope = componentCoroutineScope(DefaultNetworkCoroutineContext)

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
        coroutineScope.launch {
            val result = authorizationRepository.register(
                username = usernameState.value,
                password = passwordState.value
            )
            if (result is RegisterResult.Success) onOutput(AuthorizationRegisterComponent.Output.OnAccountCreated)
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