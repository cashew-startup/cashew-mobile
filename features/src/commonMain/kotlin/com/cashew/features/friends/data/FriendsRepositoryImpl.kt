package com.cashew.features.friends.data

import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.features.friends.data.dto.FriendResponseDTO
import com.cashew.features.friends.data.dto.toDomain
import com.cashew.features.friends.domain.Friend
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.PhysicalReplica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.minutes

class FriendsRepositoryImpl(
    httpClient: HttpClient,
    replicaClient: ReplicaClient,
    credentialsStorage: CredentialsStorage
): FriendsRepository {

    override val friendsReplica: PhysicalReplica<List<Friend>> = replicaClient.createReplica(
        name = "friends_replica",
        settings = ReplicaSettings(
            staleTime = 1.minutes,
            clearTime = 5.minutes
        ),
        fetcher = {
            val username = credentialsStorage.getUsername()?.value
                ?: throw UnauthorizedException(NullPointerException("Username is null"))
            httpClient
                .get("v1/friends") {
                    parameter("username", username)
                }
                .body<FriendResponseDTO>()
                .toDomain()
        }
    )

}