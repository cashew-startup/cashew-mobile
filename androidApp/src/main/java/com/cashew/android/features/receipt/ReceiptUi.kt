package com.cashew.android.features.receipt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.features.receipt.list.ReceiptListUi
import com.cashew.features.receipt.ui.ReceiptComponent

@Composable
fun ReceiptUi(
    component: ReceiptComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStackFlow.collectAsState()

    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is ReceiptComponent.Child.List -> ReceiptListUi(component = instance.component)
        }
    }

}