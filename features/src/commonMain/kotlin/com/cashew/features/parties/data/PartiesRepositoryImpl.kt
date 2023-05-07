package com.cashew.features.parties.data

import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.features.parties.data.dto.PartiesResponseDTO
import com.cashew.features.parties.data.dto.toDomain
import com.cashew.features.parties.domain.Party
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.PhysicalReplica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.minutes

class PartiesRepositoryImpl(
    httpClient: HttpClient,
    replicaClient: ReplicaClient,
    credentialsStorage: CredentialsStorage
): PartiesRepository {
    override val partiesReplica: PhysicalReplica<List<Party>> = replicaClient.createReplica(
        name = "parties_replica",
        settings = ReplicaSettings(
            staleTime = 1.minutes,
            clearTime = 5.minutes
        ),
        fetcher = {
            val username = credentialsStorage.getUsername()?.value
                ?: throw UnauthorizedException(NullPointerException("Username is null"))
            httpClient
                .get("v1/parties/of") {
                    parameter("username", username)
                }
            .body<PartiesResponseDTO>()
            .toDomain()
        }
    )
}
