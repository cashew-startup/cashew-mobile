package com.cashew.features.friends.ui.list

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import me.aartikov.replica.single.Replica

class RealFriendsListComponent(
    componentContext: ComponentContext,
    friendsReplica: Replica<List<Friend>>
) : ComponentContext by componentContext, FriendsListComponent {

    override val friendListState: CStateFlow<List<Friend>> = CMutableStateFlow(Friend.mocks())
}