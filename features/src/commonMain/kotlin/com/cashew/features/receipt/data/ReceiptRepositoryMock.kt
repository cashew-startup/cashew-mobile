package com.cashew.features.receipt.data

import com.cashew.features.receipt.domain.Receipt
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class ReceiptRepositoryMock(
    replicaClient: ReplicaClient
) : ReceiptRepository {

    private var count = Receipt.mocks().size + 1

    override val receiptsReplica: Replica<List<Receipt>> = replicaClient.createReplica(
        name = "receipt_mock_replica",
        settings = ReplicaSettings(
            staleTime = 12.seconds
        )
    ) {
        Receipt.mocks()
    }

    override suspend fun getReceipt(receiptString: String): Receipt {
        return Receipt.mock(count++)
    }
}