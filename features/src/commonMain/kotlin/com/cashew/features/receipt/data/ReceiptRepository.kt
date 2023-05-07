package com.cashew.features.receipt.data

import com.cashew.features.receipt.domain.Receipt
import me.aartikov.replica.single.Replica

interface ReceiptRepository {

    val receiptsReplica: Replica<List<Receipt>>

    suspend fun getReceipt(receiptString: String): Receipt

}