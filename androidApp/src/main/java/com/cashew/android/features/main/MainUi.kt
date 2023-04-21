package com.cashew.android.features.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.features.profile.ProfileUi
import com.cashew.features.main.ui.MainComponent

@Composable
fun MainUi(
    component: MainComponent,
    modifier:  Modifier = Modifier
) {
    val childStack by component.childStackFlow.collectAsState()
    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is MainComponent.Child.Profile -> ProfileUi(component = instance.component)
            is MainComponent.Child.Receipt -> {}
        }
    }
}