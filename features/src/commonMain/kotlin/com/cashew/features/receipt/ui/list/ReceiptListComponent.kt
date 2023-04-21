package com.cashew.features.receipt.ui.list

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.domain.ReceiptId

interface ReceiptListComponent {

    val receiptListState: CStateFlow<List<Receipt>>

    val searchText: CStateFlow<String>

    fun onSearch(query: String)

    fun onSettingsClick()

    fun onScanReceiptClick()

    fun onReceiptClick()

    fun onDeleteReceiptClick(receiptId: ReceiptId)

    fun onReceiptOptionsClick()

    sealed interface Output {

    }
}