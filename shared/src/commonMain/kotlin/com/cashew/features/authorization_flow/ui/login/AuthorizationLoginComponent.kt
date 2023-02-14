package com.cashew.features.authorization_flow.ui.login

import kotlinx.coroutines.flow.StateFlow

interface AuthorizationLoginComponent {

    val usernameState: StateFlow<String>
    val passwordState: StateFlow<String>

    fun onLoginClick()
    fun onCreateNewAccountClick()

    fun onUsernameTextChanged(changedUsername: String)
    fun onPasswordTextChanged(changedPassword: String)


    sealed interface Output {
        object OnLoggedIn : Output
        object OnCreateNewAccountRequested : Output
    }

}