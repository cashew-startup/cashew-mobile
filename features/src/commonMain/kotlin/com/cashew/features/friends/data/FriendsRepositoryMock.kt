package com.cashew.features.friends.data

import com.cashew.features.friends.domain.Friend
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class FriendsRepositoryMock(replicaClient: ReplicaClient) : FriendsRepository {

    override val friendsReplica: Replica<List<Friend>> = replicaClient.createReplica(
        name = "friends_mock_replica",
        settings = ReplicaSettings(
            staleTime = 12.seconds
        )
    ) {
        Friend.mocks()
    }
}