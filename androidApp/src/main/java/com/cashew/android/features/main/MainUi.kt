package com.cashew.android.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.core.ui.widgets.NavigationalBottomBar
import com.cashew.android.features.profile.ProfileUi
import com.cashew.android.features.receipt.ReceiptUi
import com.cashew.features.main.ui.MainComponent

@Composable
fun MainUi(
    component: MainComponent,
    modifier:  Modifier = Modifier
) {
    val childStack by component.childStackFlow.collectAsState()
    val activeTabState by component.activeTabState.collectAsState()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationalBottomBar(
                activeTab = activeTabState,
                onActiveTabChanged = component::onActiveTabChanged
            )
        }
    ) { paddingValues ->
        Children(
            stack = childStack,
            modifier = Modifier.padding(paddingValues)
        ) { child ->
            when (val instance = child.instance) {
                is MainComponent.Child.Profile -> ProfileUi(component = instance.component)
                is MainComponent.Child.Receipt -> ReceiptUi(component = instance.component)
            }
        }
    }
}