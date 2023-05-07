package com.cashew.features.friends.ui.list

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.observe
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import me.aartikov.replica.single.Loadable
import me.aartikov.replica.single.Replica

class RealFriendsListComponent(
    componentContext: ComponentContext,
    friendsReplica: Replica<List<Friend>>
) : ComponentContext by componentContext, FriendsListComponent {

    override val friendListState: CStateFlow<Loadable<List<Friend>>> = friendsReplica.observe(lifecycle)
}