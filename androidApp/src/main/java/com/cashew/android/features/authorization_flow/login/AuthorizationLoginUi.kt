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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.R
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthorizationLoginUi(
    component: com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent,
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
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 58.dp),
        ) {
            Title(
                text = stringResource(id = R.string.login_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )
            PrimaryTextField(
                text = usernameText,
                hint = stringResource(id = R.string.login_username),
                onTextChange = component::onUsernameTextChanged,
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
                hint = stringResource(id = R.string.login_password),
                onTextChange = component::onPasswordTextChanged,
                isError = isPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { component.onLoginClick() }
                )
            )

            if (errors.isEmpty()) {
                Spacer(modifier = Modifier.height(42.dp))
            } else {
                LazyColumn {
                    items(errors) { error ->
                        Error(
                            text = stringResource(id = getTextFromError(error)),
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
                text = stringResource(id = R.string.login_button),
                onClick = component::onLoginClick,
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = component::onCreateNewAccountClick,
                modifier = Modifier
                    .align(CenterHorizontally)
            ) {
                Text(
                    text = stringResource(id = R.string.login_text_button),
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
}

@Composable
fun getTextFromError(error: com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent.Error): Int {
    return when (error) {
        com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent.Error.InvalidCredentials -> R.string.login_error_invalid_credentials
    }
}

@Preview
@Composable
fun AuthorizationLoginUiPreview() {
    AppTheme {
        AuthorizationLoginUi(FakeAuthorizationLoginComponent())
    }
}

class FakeAuthorizationLoginComponent :
    com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent {

    override val usernameState: StateFlow<String> = MutableStateFlow("Username")
    override val passwordState: StateFlow<String> = MutableStateFlow("Password")
    override val isUsernameErrorState: StateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: StateFlow<Boolean> = MutableStateFlow(true)
    override val errorsState: StateFlow<List<com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent.Error>> =
        MutableStateFlow(listOf(com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent.Error.InvalidCredentials))

    override fun onLoginClick() = Unit
    override fun onCreateNewAccountClick() = Unit
    override fun onUsernameTextChanged(changedUsername: String) = Unit
    override fun onPasswordTextChanged(changedPassword: String) = Unit
}