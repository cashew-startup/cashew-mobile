package com.cashew.features.receipt.ui.list

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.domain.ReceiptId

class RealReceiptListComponent(
    componentContext: ComponentContext,
    onOutput: (ReceiptListComponent.Output) -> Unit
) : ComponentContext by componentContext, ReceiptListComponent {

    override val receiptListState: CMutableStateFlow<List<Receipt>> = CMutableStateFlow(Receipt.mocks())

    override val searchText: CMutableStateFlow<String> = CMutableStateFlow("")

    override fun onSearch(query: String) {
        searchText.value = query
    }

    override fun onSettingsClick() = Unit // TODO

    override fun onScanReceiptClick() = Unit // TODO

    override fun onReceiptClick() = Unit // TODO

    override fun onDeleteReceiptClick(receiptId: ReceiptId) {
        receiptListState.value = receiptListState.value.filterNot { it.id == receiptId }
    }

    override fun onReceiptOptionsClick() = Unit // TODO
}