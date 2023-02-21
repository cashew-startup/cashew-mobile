package com.cashew.android.features.authorization_flow.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.R
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.*
import com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthorizationRegisterUi(
    component: com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent,
    modifier: Modifier = Modifier
) {
    val isUsernameError by component.isUsernameErrorState.collectAsState()
    val isPasswordError by component.isPasswordErrorState.collectAsState()
    val isConfirmPasswordError by component.isConfirmPasswordState.collectAsState()
    val errors by component.errorsState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_32_arrow_back),
                        tint = CashewTheme.colors.icons.primary,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                },
                onNavigationIconClick = component::onBackPressed
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 58.dp),
        ) {
            Title(
                text = stringResource(id = R.string.register_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 30.dp
                    )
            )
            PrimaryTextField(
                hint = stringResource(id = R.string.register_username),
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
                hint = stringResource(id = R.string.register_password),
                onTextChange = component::onPasswordTextChanged,
                isError = isPasswordError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            PrimaryTextField(
                hint = stringResource(id = R.string.register_confirm_password),
                onTextChange = component::onConfirmPasswordChanged,
                isError = isConfirmPasswordError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { component.onCreateClick() }
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
                text = stringResource(id = R.string.register_button),
                onClick = component::onCreateClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun getTextFromError(error: com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error): Int {
    return when (error) {
        com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error.ShortUsername -> R.string.register_error_short_username
        com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error.ShortPassword -> R.string.register_error_short_password
        com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error.PasswordsNotMatch -> R.string.register_error_passwords_not_match
        com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error.UserAlreadyExists -> R.string.register_error_user_already_exists
    }

}

@Preview
@Composable
fun AuthorizationRegisterUiPreview() {
    AppTheme {
        AuthorizationRegisterUi(FakeAuthorizationRegisterComponent())
    }
}

class FakeAuthorizationRegisterComponent :
    com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent {
    override val usernameState: StateFlow<String> = MutableStateFlow("")
    override val passwordState: StateFlow<String> = MutableStateFlow("")
    override val confirmPasswordState: StateFlow<String> = MutableStateFlow("")
    override val isUsernameErrorState: StateFlow<Boolean> = MutableStateFlow(false)
    override val isPasswordErrorState: StateFlow<Boolean> = MutableStateFlow(true)
    override val isConfirmPasswordState: StateFlow<Boolean> = MutableStateFlow(true)
    override val errorsState: StateFlow<List<com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error>> =
        MutableStateFlow(listOf(com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent.Error.PasswordsNotMatch))

    override fun onCreateClick() = Unit
    override fun onBackPressed() = Unit
    override fun onUsernameTextChanged(changedUsername: String) = Unit
    override fun onPasswordTextChanged(changedPassword: String) = Unit
    override fun onConfirmPasswordChanged(changedConfirmPassword: String) = Unit
}