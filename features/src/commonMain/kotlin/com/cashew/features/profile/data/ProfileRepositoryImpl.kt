package com.cashew.features.profile.data

import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.features.profile.data.dto.ProfileResponseDTO
import com.cashew.features.profile.data.dto.toDomain
import com.cashew.features.profile.domain.Profile
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.PhysicalReplica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class ProfileRepositoryImpl(
    httpClient: HttpClient,
    replicaClient: ReplicaClient,
    credentialsStorage: CredentialsStorage
) : ProfileRepository {

    override val profileReplica: PhysicalReplica<Profile> = replicaClient
        .createReplica(
            name = "profileReplica",
            settings = ReplicaSettings(
                staleTime = 10.seconds,
                clearTime = 1.minutes
            )
        ) {
            val username = credentialsStorage.getUsername()?.value
                ?: throw UnauthorizedException(NullPointerException("Username is null"))
            try {
                httpClient
                    .get("v1/users/byUsername") {
                        parameter("username", username)
                    }
                    .body<ProfileResponseDTO>()
                    .toDomain()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
}