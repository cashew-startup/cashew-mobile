package com.cashew.features.authorization_flow.ui.login

import com.cashew.features.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
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

    sealed class Error(val text: StringDesc?) {
        object InvalidCredentials : Error(MR.strings.login_error_invalid_credentials.desc())
    }

    sealed interface Output {
        object OnLoggedIn : Output
        object OnCreateNewAccountRequested : Output
    }

}