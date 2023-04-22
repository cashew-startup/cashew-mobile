package com.cashew.features.friends.data

import com.cashew.features.friends.domain.Friend
import me.aartikov.replica.single.Replica

interface FriendsRepository {

    val friendsReplica: Replica<List<Friend>>

}