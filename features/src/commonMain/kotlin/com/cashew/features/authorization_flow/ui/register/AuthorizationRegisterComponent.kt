package com.cashew.features.authorization_flow.ui.register

import com.cashew.features.MR
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
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

    sealed class Error(val text: StringDesc?) {
        object ShortUsername : Error(MR.strings.register_error_short_username.desc())
        object ShortPassword : Error(MR.strings.register_error_short_password.desc())
        object PasswordsNotMatch : Error(MR.strings.register_error_passwords_not_match.desc())
        object UserAlreadyExists : Error(MR.strings.register_error_user_already_exists.desc())
    }

    sealed interface Output {
        object OnAccountCreated : Output
        object OnBackPressed : Output
    }

}