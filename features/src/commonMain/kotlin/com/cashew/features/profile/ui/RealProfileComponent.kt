package com.cashew.features.profile.ui

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.observe
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.profile.domain.Profile
import me.aartikov.replica.single.Replica

class RealProfileComponent(
    componentContext: ComponentContext,
    profileReplica: Replica<Profile>
) : ComponentContext by componentContext, ProfileComponent {

    override val profile = profileReplica.observe(lifecycle)
    override val mode: CMutableStateFlow<ProfileComponent.Mode> =
        CMutableStateFlow(ProfileComponent.Mode.Viewing)
    override val currentPassword = CMutableStateFlow("")
    override val newPassword = CMutableStateFlow("")
    override val confirmNewPassword = CMutableStateFlow("")
    override val username = CMutableStateFlow("")

    override fun onCurrentPasswordChanged(password: String) {
        currentPassword.value = password
    }

    override fun onNewPasswordChanged(password: String) {
        newPassword.value = password
    }

    override fun onConfirmNewPasswordChanged(password: String) {
        confirmNewPassword.value = password
    }

    override fun onUsernameChanged(username: String) {
        this.username.value = username
    }

    override fun onChangePasswordClick() {
        mode.value = ProfileComponent.Mode.EditingPassword
    }

    override fun onDeleteAccountClick() = Unit

    override fun onEditProfileClick() {
        mode.value = ProfileComponent.Mode.EditingProfile
    }

    override fun onSaveChangesClick() = Unit

    override fun onSavePasswordClick() = Unit
}