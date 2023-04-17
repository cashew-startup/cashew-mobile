package com.cashew.features.profile.ui

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.profile.domain.Profile
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import me.aartikov.replica.single.Loadable

interface ProfileComponent {

    val profile: CStateFlow<Loadable<Profile>>

    val mode: CStateFlow<Mode>

    val currentPassword: CStateFlow<String>

    val newPassword: CStateFlow<String>

    val confirmNewPassword: CStateFlow<String>

    val username: CStateFlow<String>

    fun onCurrentPasswordChanged(password: String)

    fun onNewPasswordChanged(password: String)

    fun onConfirmNewPasswordChanged(password: String)

    fun onUsernameChanged(username: String)

    fun onChangePasswordClick()

    fun onDeleteAccountClick()

    fun onEditProfileClick()

    fun onSaveChangesClick()

    fun onSavePasswordClick()

    sealed class Error(val message: StringDesc) {
        object PasswordNotMatch : Error(MR.strings.register_error_passwords_not_match.desc())

    }

    sealed interface Mode {
        object EditingProfile : Mode
        object EditingPassword : Mode
        object Viewing : Mode
    }

}