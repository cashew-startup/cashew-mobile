package com.cashew.features.friends.ui.list

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend

interface FriendsListComponent {

    val friendListState: CStateFlow<List<Friend>>

    sealed interface Output {

    }
}