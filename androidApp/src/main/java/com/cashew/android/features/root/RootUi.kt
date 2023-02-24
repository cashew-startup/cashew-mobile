package com.cashew.android.features.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.core.message.MessageUi
import com.cashew.android.features.authorization_flow.AuthorizationFlowUi
import com.cashew.android.features.welcome.WelcomeUi
import com.cashew.features.root.ui.RootComponent

@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier
) {

    val childStack by component.childStackFlow.collectAsState()

    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Welcome -> WelcomeUi(component = instance.component)
            is RootComponent.Child.AuthorizationFlow -> AuthorizationFlowUi(component = instance.component)
        }
    }

    MessageUi(component = component.messageComponent)
}