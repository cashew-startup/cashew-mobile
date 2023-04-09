package com.cashew.features.receipt.data

import com.cashew.features.receipt.domain.Receipt

interface ReceiptRepository {

    suspend fun getReceipt(receiptString: String): Receipt

}