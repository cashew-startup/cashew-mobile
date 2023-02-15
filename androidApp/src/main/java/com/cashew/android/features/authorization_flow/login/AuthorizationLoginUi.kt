package com.cashew.android.features.authorization_flow.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.R
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.ui.widgets.Error
import com.cashew.android.core.ui.widgets.PrimaryTextField
import com.cashew.android.core.ui.widgets.Title
import com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthorizationLoginUi(
    component: AuthorizationLoginComponent,
    modifier: Modifier = Modifier
) {

    val isUsernameError by component.isUsernameErrorState.collectAsState()
    val isPasswordError by component.isPasswordErrorState.collectAsState()
    val errors by component.errorsState.collectAsState()

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 58.dp),
        ) {
            Title(
                text = stringResource(id = R.string.login_title),
                modifier = Modifier.fillMaxWidth()
            )
            PrimaryTextField(
                hint = stringResource(id = R.string.login_username),
                onTextChange = component::onUsernameTextChanged,
                isError = isUsernameError

            )
            PrimaryTextField(
                hint = stringResource(id = R.string.login_password),
                onTextChange = component::onPasswordTextChanged,
                isError = isPasswordError
            )

            LazyColumn {
                items(errors) { error ->
                    Error(text = error.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun AuthorizationLoginUiPreview() {
    AppTheme {
        AuthorizationLoginUi(FakeAuthorizationLoginComponent())
    }
}

class FakeAuthorizationLoginComponent : AuthorizationLoginComponent {

    override val usernameState: StateFlow<String> = MutableStateFlow("Username")
    override val passwordState: StateFlow<String> = MutableStateFlow("Password")
    override val isUsernameErrorState: StateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: StateFlow<Boolean> = MutableStateFlow(false)
    override val errorsState: StateFlow<List<AuthorizationLoginComponent.Error>> =
        MutableStateFlow(emptyList())

    override fun onLoginClick() = Unit
    override fun onCreateNewAccountClick() = Unit
    override fun onUsernameTextChanged(changedUsername: String) = Unit
    override fun onPasswordTextChanged(changedPassword: String) = Unit
}