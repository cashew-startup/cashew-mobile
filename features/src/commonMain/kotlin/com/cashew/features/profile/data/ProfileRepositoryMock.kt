package com.cashew.features.profile.data

import com.cashew.features.profile.domain.Profile
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class ProfileRepositoryMock(replicaClient: ReplicaClient) : ProfileRepository {

    override val profileReplica: Replica<Profile> = replicaClient.createReplica(
        name = "profile_replica_mock",
        settings = ReplicaSettings(
            staleTime = 12.seconds
        )
    ) {
        Profile.mock()
    }
}