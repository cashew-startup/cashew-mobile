package com.cashew.android.features.authorization_flow.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.*
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent

@Composable
fun AuthorizationLoginUi(
    component: AuthorizationLoginComponent,
    modifier: Modifier = Modifier
) {

    val usernameText by component.usernameState.collectAsState()
    val passwordText by component.passwordState.collectAsState()

    val isUsernameError by component.isUsernameErrorState.collectAsState()
    val isPasswordError by component.isPasswordErrorState.collectAsState()
    val errors by component.errorsState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { Toolbar() }
    ) { paddingValues ->
        AuthorizationLoginContent(
            usernameText = usernameText,
            onUsernameTextChanged = component::onUsernameTextChanged,
            isUsernameError = isUsernameError,
            passwordText = passwordText,
            onPasswordTextChanged = component::onPasswordTextChanged,
            isPasswordError = isPasswordError,
            errors = errors,
            onLoginClick = component::onLoginClick,
            onCreateNewAccountClick = component::onCreateNewAccountClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AuthorizationLoginContent(
    usernameText: String,
    onUsernameTextChanged: (String) -> Unit,
    isUsernameError: Boolean,
    passwordText: String,
    onPasswordTextChanged: (String) -> Unit,
    isPasswordError: Boolean,
    errors: List<AuthorizationLoginComponent.Error>,
    onLoginClick: () -> Unit,
    onCreateNewAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 58.dp),
    ) {
        Title(
            text = MR.strings.login_title.resolve(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )

        PrimaryTextField(
            text = usernameText,
            placeholder = MR.strings.login_username.resolve(),
            onTextChange = onUsernameTextChanged,
            isError = isUsernameError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp
                ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        PrimaryTextField(
            text = passwordText,
            placeholder = MR.strings.login_password.resolve(),
            onTextChange = onPasswordTextChanged,
            isError = isPasswordError,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onLoginClick() }
            )
        )

        if (errors.isEmpty()) {
            Spacer(modifier = Modifier.height(42.dp))
        } else {
            LazyColumn {
                items(errors) { error ->
                    Error(
                        text = error.text?.resolve() ?: "",
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 13.dp,
                            bottom = 13.dp
                        )
                    )
                }
            }
        }

        PrimaryButton(
            text = MR.strings.login_button.resolve(),
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(
            onClick = onCreateNewAccountClick,
            modifier = Modifier
                .align(CenterHorizontally)
        ) {
            Text(
                text = MR.strings.login_text_button.resolve(),
                style = CashewTheme.typography.text.regular.merge(
                    TextStyle(
                        textDecoration = TextDecoration.Underline,
                        color = CashewTheme.colors.text.primary
                    )
                )
            )
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

    override val usernameState: CStateFlow<String> = CMutableStateFlow("Username")
    override val passwordState: CStateFlow<String> = CMutableStateFlow("Password")
    override val isUsernameErrorState: CStateFlow<Boolean> = CMutableStateFlow(false)
    override val isPasswordErrorState: CStateFlow<Boolean> = CMutableStateFlow(true)
    override val errorsState: CStateFlow<List<AuthorizationLoginComponent.Error>> =
        CMutableStateFlow(listOf(AuthorizationLoginComponent.Error.InvalidCredentials))

    override fun onLoginClick() = Unit
    override fun onCreateNewAccountClick() = Unit
    override fun onUsernameTextChanged(changedUsername: String) = Unit
    override fun onPasswordTextChanged(changedPassword: String) = Unit
}