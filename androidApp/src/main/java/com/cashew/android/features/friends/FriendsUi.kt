package com.cashew.android.features.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.cashew.android.features.friends.list.FriendsListUi
import com.cashew.features.friends.ui.FriendsComponent


@Composable
fun FriendsUi(
    component: FriendsComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStackFlow.collectAsState()

    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is FriendsComponent.Child.List -> FriendsListUi(component = instance.component)
        }
    }
}
