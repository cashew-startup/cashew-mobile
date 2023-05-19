package com.cashew.features.receipt.data

import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.features.receipt.data.dto.ReceiptRequestDTO
import com.cashew.features.receipt.data.dto.ReceiptResponseDTO
import com.cashew.features.receipt.data.dto.toDomain
import com.cashew.features.receipt.domain.Receipt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.minutes

class ReceiptRepositoryImpl(
    private val httpClient: HttpClient,
    replicaClient: ReplicaClient,
    private val credentialsStorage: CredentialsStorage
) : ReceiptRepository {

    override val receiptsReplica: Replica<List<Receipt>> = replicaClient.createReplica(
        name = "receiptsReplica",
        settings = ReplicaSettings(staleTime = 1.minutes),
    ) {
        val username = credentialsStorage.getUsername()
            ?: throw UnauthorizedException(NullPointerException("Username is null"))
        Receipt.mocks()
    }



    override suspend fun getReceipt(receiptString: String): Receipt {
        val username = credentialsStorage.getUsername()?.value
            ?: throw UnauthorizedException(NullPointerException("null credentials"))
        return httpClient.post("/receipt") {
            setBody(
                ReceiptRequestDTO(
                    username = username,
                    receiptString = receiptString
                )
            )
        }
            .body<ReceiptResponseDTO>()
            .toDomain()
    }


}