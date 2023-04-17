package com.cashew.android.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.*
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.profile.domain.Profile
import com.cashew.features.profile.ui.ProfileComponent
import me.aartikov.replica.single.Loadable

@Composable
fun ProfileUi(
    component: ProfileComponent,
    modifier: Modifier = Modifier
) {
    val mode by component.mode.collectAsState()
    val profileState by component.profile.collectAsState()
    val currentPassword by component.currentPassword.collectAsState()
    val newPassword by component.newPassword.collectAsState()
    val confirmNewPassword by component.confirmNewPassword.collectAsState()
    val username by component.username.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(

            )
        }
    ) { paddingValues ->
        ReplicaLceWidget(loadable = profileState) { profile ->
            ProfileContent(
                mode = mode,
                profile = profile,
                currentPassword = currentPassword,
                onCurrentPasswordChanged = component::onCurrentPasswordChanged,
                newPassword = newPassword,
                onNewPasswordChanged = component::onNewPasswordChanged,
                confirmNewPassword = confirmNewPassword,
                onConfirmNewPasswordChanged = component::onConfirmNewPasswordChanged,
                onSaveNewPasswordClick = component::onSavePasswordClick,
                username = username,
                onUsernameChanged = component::onUsernameChanged,
                onSaveChangesClick = component::onSaveChangesClick,
                onChangePasswordClick = component::onChangePasswordClick,
                onDeleteAccountClick = component::onDeleteAccountClick,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun ProfileContent(
    mode: ProfileComponent.Mode,
    profile: Profile,
    currentPassword: String,
    onCurrentPasswordChanged: (String) -> Unit,
    newPassword: String,
    onNewPasswordChanged: (String) -> Unit,
    confirmNewPassword: String,
    onConfirmNewPasswordChanged: (String) -> Unit,
    onSaveNewPasswordClick: () -> Unit,
    username: String,
    onUsernameChanged: (String) -> Unit,
    onSaveChangesClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (mode) {
        ProfileComponent.Mode.EditingPassword -> {
            ProfileEditingPasswordContent(
                profile = profile,
                currentPassword = currentPassword,
                onCurrentPasswordChanged = onCurrentPasswordChanged,
                newPassword = newPassword,
                onNewPasswordChanged = onNewPasswordChanged,
                confirmNewPassword = confirmNewPassword,
                onConfirmNewPasswordChanged = onConfirmNewPasswordChanged,
                onSaveNewPasswordClick = onSaveNewPasswordClick,
                modifier = modifier
            )
        }
        ProfileComponent.Mode.EditingProfile -> {
            ProfileEditingProfileContent(
                profile = profile,
                username = username,
                onUsernameChanged = onUsernameChanged,
                onSaveChangesClick = onSaveChangesClick,
                modifier = modifier
            )
        }
        ProfileComponent.Mode.Viewing -> {
            ProfileViewingContent(
                profile = profile,
                onChangePasswordClick = onChangePasswordClick,
                onDeleteAccountClick = onDeleteAccountClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ProfileViewingContent(
    profile: Profile,
    onChangePasswordClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Title(text = profile.username.value)

        Text(
            text = MR.strings.profile_mail.resolve(),
            style = CashewTheme.typography.text.bold
        )

        Text(
            text = profile.email,
            style = CashewTheme.typography.text.regular
        )

        Text(
            text = MR.strings.profile_change_password.resolve(),
            style = CashewTheme.typography.text.bold,
            modifier = Modifier.clickable(onClick = onChangePasswordClick)
        )

        Text(
            text = MR.strings.profile_delete_account.resolve(),
            style = CashewTheme.typography.text.bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Row(
            modifier = Modifier.clickable(onClick = onDeleteAccountClick)
        ) {
            Icon(
                painter = MR.assets.IcDelete32.painter(),
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp),
                tint = CashewTheme.colors.icons.delete
            )
            Text(
                text = MR.strings.profile_delete_account.resolve(),
                style = CashewTheme.typography.text.bold,
                color = CashewTheme.colors.text.delete
            )
        }
    }
}

@Composable
fun ProfileEditingPasswordContent(
    profile: Profile,
    currentPassword: String,
    onCurrentPasswordChanged: (String) -> Unit,
    newPassword: String,
    onNewPasswordChanged: (String) -> Unit,
    confirmNewPassword: String,
    onConfirmNewPasswordChanged: (String) -> Unit,
    onSaveNewPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = MR.strings.profile_mail.resolve(),
            style = CashewTheme.typography.text.bold
        )
        Text(
            text = MR.strings.profile_mail.resolve()
        )
        Text(
            text = profile.email,
            style = CashewTheme.typography.text.regular
        )
        Text(
            text = MR.strings.profile_change_password.resolve(),
            style = CashewTheme.typography.text.bold
        )
        PrimaryTextField(
            text = currentPassword,
            placeholder = MR.strings.profile_current_password.resolve(),
            onTextChange = onCurrentPasswordChanged
        )
        PrimaryTextField(
            text = newPassword,
            placeholder = MR.strings.profile_new_password.resolve(),
            onTextChange = onNewPasswordChanged
        )
        PrimaryTextField(
            text = confirmNewPassword,
            placeholder = MR.strings.profile_confirm_new_password.resolve(),
            onTextChange = onConfirmNewPasswordChanged
        )
        PrimaryButton(
            text = MR.strings.profile_button_save_new_password.resolve(),
            onClick = onSaveNewPasswordClick
        )
    }
}

@Composable
fun ProfileEditingProfileContent(
    profile: Profile,
    username: String,
    onUsernameChanged: (String) -> Unit,
    onSaveChangesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        PrimaryTextField(
            text = username,
            placeholder = "",
            onTextChange = onUsernameChanged
        )
        Text(
            text = MR.strings.profile_mail.resolve(),
            style = CashewTheme.typography.text.bold
        )

        Text(
            text = MR.strings.profile_mail.resolve()
        )

        Text(
            text = profile.email,
            style = CashewTheme.typography.text.regular
        )

        Text(
            text = MR.strings.profile_delete_account.resolve(),
            style = CashewTheme.typography.text.bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        PrimaryButton(
            text = MR.strings.profile_button_save_changes.resolve(),
            onClick = onSaveChangesClick
        )
    }
}

class FakeProfileComponent : ProfileComponent {
    override val profile: CStateFlow<Loadable<Profile>> =
        CMutableStateFlow(Loadable())
    override val mode: CStateFlow<ProfileComponent.Mode> =
        CMutableStateFlow(ProfileComponent.Mode.Viewing)
    override val currentPassword: CStateFlow<String> = CMutableStateFlow("")
    override val newPassword: CStateFlow<String> = CMutableStateFlow("")
    override val confirmNewPassword: CStateFlow<String> = CMutableStateFlow("")
    override val username: CStateFlow<String> = CMutableStateFlow("")

    override fun onCurrentPasswordChanged(password: String) = Unit

    override fun onNewPasswordChanged(password: String) = Unit

    override fun onConfirmNewPasswordChanged(password: String) = Unit

    override fun onUsernameChanged(username: String) = Unit

    override fun onChangePasswordClick() = Unit

    override fun onDeleteAccountClick() = Unit

    override fun onEditProfileClick() = Unit

    override fun onSaveChangesClick() = Unit

    override fun onSavePasswordClick() = Unit
}