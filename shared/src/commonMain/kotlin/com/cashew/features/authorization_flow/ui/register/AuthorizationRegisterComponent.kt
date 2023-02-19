package com.cashew.features.authorization_flow.ui.register

import kotlinx.coroutines.flow.StateFlow

interface AuthorizationRegisterComponent {

    val usernameState: StateFlow<String>
    val passwordState: StateFlow<String>
    val confirmPasswordState: StateFlow<String>

    val isUsernameErrorState: StateFlow<Boolean>
    val isPasswordErrorState: StateFlow<Boolean>
    val isConfirmPasswordState: StateFlow<Boolean>

    val errorsState: StateFlow<List<Error>>

    fun onCreateClick()

    fun onBackPressed()

    fun onUsernameTextChanged(changedUsername: String)
    fun onPasswordTextChanged(changedPassword: String)
    fun onConfirmPasswordChanged(changedConfirmPassword: String)

    enum class Error {
        ShortUsername, ShortPassword, PasswordsNotMatch, UserAlreadyExists
    }

    sealed interface Output {
        object OnAccountCreated : Output
        object OnBackPressed : Output
    }

}