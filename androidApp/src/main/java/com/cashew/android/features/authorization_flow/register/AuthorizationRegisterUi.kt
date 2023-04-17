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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.*
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent

@Composable
fun AuthorizationRegisterUi(
    component: AuthorizationRegisterComponent,
    modifier: Modifier = Modifier
) {
    val username by component.usernameState.collectAsState()
    val password by component.passwordState.collectAsState()
    val confirmPassword by component.confirmPasswordState.collectAsState()
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
                        painter = MR.assets.IcArrowBack32.painter(),
                        tint = CashewTheme.colors.icons.primary,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                },
                onNavigationIconClick = component::onBackPressed
            )
        }
    ) { paddingValues ->
        AuthorizationRegisterContent(
            username = username,
            onUsernameTextChanged = component::onUsernameTextChanged,
            isUsernameError = isUsernameError,
            password = password,
            onPasswordTextChanged = component::onPasswordTextChanged,
            isPasswordError = isPasswordError,
            confirmPassword = confirmPassword,
            onConfirmPasswordChanged = component::onConfirmPasswordChanged,
            isConfirmPasswordError = isConfirmPasswordError,
            onCreateClick = component::onCreateClick,
            errors = errors,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AuthorizationRegisterContent(
    username: String,
    onUsernameTextChanged: (String) -> Unit,
    isUsernameError: Boolean,
    password: String,
    onPasswordTextChanged: (String) -> Unit,
    isPasswordError: Boolean,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    isConfirmPasswordError: Boolean,
    onCreateClick: () -> Unit,
    errors: List<AuthorizationRegisterComponent.Error>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 58.dp),
    ) {
        Title(
            text = MR.strings.register_title.resolve(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 30.dp
                )
        )
        PrimaryTextField(
            text = username,
            placeholder = MR.strings.register_username.resolve(),
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
            text = password,
            placeholder = MR.strings.register_password.resolve(),
            onTextChange = onPasswordTextChanged,
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
            text = confirmPassword,
            placeholder = MR.strings.register_confirm_password.resolve(),
            onTextChange = onConfirmPasswordChanged,
            isError = isConfirmPasswordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onCreateClick() }
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
            text = MR.strings.register_button.resolve(),
            onClick = onCreateClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun AuthorizationRegisterUiPreview() {
    AppTheme {
        AuthorizationRegisterUi(FakeAuthorizationRegisterComponent())
    }
}

class FakeAuthorizationRegisterComponent : AuthorizationRegisterComponent {

    override val usernameState: CStateFlow<String> = CMutableStateFlow("")
    override val passwordState: CStateFlow<String> = CMutableStateFlow("")
    override val confirmPasswordState: CStateFlow<String> = CMutableStateFlow("")
    override val isUsernameErrorState: CStateFlow<Boolean> = CMutableStateFlow(false)
    override val isPasswordErrorState: CStateFlow<Boolean> = CMutableStateFlow(true)
    override val isConfirmPasswordState: CStateFlow<Boolean> = CMutableStateFlow(true)
    override val errorsState: CStateFlow<List<AuthorizationRegisterComponent.Error>> =
        CMutableStateFlow(listOf(AuthorizationRegisterComponent.Error.PasswordsNotMatch))

    override fun onCreateClick() = Unit
    override fun onBackPressed() = Unit
    override fun onUsernameTextChanged(changedUsername: String) = Unit
    override fun onPasswordTextChanged(changedPassword: String) = Unit
    override fun onConfirmPasswordChanged(changedConfirmPassword: String) = Unit
}