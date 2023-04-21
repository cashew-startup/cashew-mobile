package com.cashew.features.friends.ui.list

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend

class RealFriendsListComponent(
    componentContext: ComponentContext,
    private val onOutput: (FriendsListComponent.Output) -> Unit
) : ComponentContext by componentContext, FriendsListComponent {

    override val friendListState: CStateFlow<List<Friend>> = CMutableStateFlow(Friend.mocks())
}