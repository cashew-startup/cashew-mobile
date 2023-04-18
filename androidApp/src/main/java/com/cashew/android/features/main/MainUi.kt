package com.cashew.android.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.ui.widgets.NavigationalBottomBar
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
        }
    }
}