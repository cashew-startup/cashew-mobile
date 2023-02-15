package com.cashew.features.authorization_flow.ui.login

import kotlinx.coroutines.flow.StateFlow

interface AuthorizationLoginComponent {

    val usernameState: StateFlow<String>
    val passwordState: StateFlow<String>

    val isUsernameErrorState: StateFlow<Boolean>
    val isPasswordErrorState: StateFlow<Boolean>

    val errorsState: StateFlow<List<Error>>

    fun onLoginClick()
    fun onCreateNewAccountClick()

    fun onUsernameTextChanged(changedUsername: String)
    fun onPasswordTextChanged(changedPassword: String)

    enum class Error {
        InvalidCredentials
    }

    sealed interface Output {
        object OnLoggedIn : Output
        object OnCreateNewAccountRequested : Output
    }

}