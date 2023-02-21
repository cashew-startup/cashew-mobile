package com.cashew.android.features.authorization_flow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.features.authorization_flow.login.AuthorizationLoginUi
import com.cashew.android.features.authorization_flow.register.AuthorizationRegisterUi
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent

@Composable
fun AuthorizationFlowUi(
    component: com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent,
    modifier: Modifier = Modifier
) {

    val childStack by component.childStackFlow.collectAsState()

    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent.Child.Login -> AuthorizationLoginUi(instance.component)
            is com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent.Child.Register -> AuthorizationRegisterUi(instance.component)
        }
    }
}