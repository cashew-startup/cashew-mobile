package com.cashew.features.friends.ui.list

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import me.aartikov.replica.single.Loadable

interface FriendsListComponent {

    val friendListState: CStateFlow<Loadable<List<Friend>>>

    sealed interface Output {

    }
}