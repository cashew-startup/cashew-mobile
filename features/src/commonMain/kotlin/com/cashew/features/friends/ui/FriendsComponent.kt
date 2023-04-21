package com.cashew.features.friends.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.ui.list.FriendsListComponent

interface FriendsComponent {

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: FriendsListComponent) : Child
    }

}