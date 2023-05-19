package com.cashew.features.receipt.ui.details

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.utils.componentCoroutineScope
import com.cashew.core.utils.safeLaunch
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.ProductId
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.domain.ReceiptId
import me.aartikov.replica.single.Replica

class RealReceiptDetailsComponent(
    componentContext: ComponentContext,
    private val receiptId: ReceiptId,
    private val friendsReplica: Replica<List<Friend>>,
    private val receiptsReplica: Replica<List<Receipt>>,
    private val exceptionHandler: ExceptionHandler,
    private val onOutput: (ReceiptDetailsComponent.Output) -> Unit
) : ComponentContext by componentContext, ReceiptDetailsComponent {

    private val coroutineScope = componentCoroutineScope()

    override val searchTextState: CMutableStateFlow<String> = CMutableStateFlow("")

    override val originalReceipt: CMutableStateFlow<Receipt> = CMutableStateFlow(Receipt.mock(0))

    override val nameState: CMutableStateFlow<String> = CMutableStateFlow("")

    override val allPayers: CMutableStateFlow<List<Friend>> = CMutableStateFlow(emptyList())

    override val productsState: CMutableStateFlow<List<ProductViewData>> = CMutableStateFlow(emptyList())

    init {
        coroutineScope.safeLaunch(exceptionHandler) {
            val friends = friendsReplica.getData()
            originalReceipt.value = receiptsReplica.getData().first { it.id == receiptId }
            allPayers.value = friends
            productsState.value = originalReceipt.value.products.map { it.toViewData(allPayers.value) }

        }
    }

    override fun onNameChanged(name: String) {
        nameState.value = name
    }

    override fun onSearch(text: String) {
        searchTextState.value = text
    }

    override fun onSaveClick() {
        onOutput(ReceiptDetailsComponent.Output.OnReceiptSaved)
    }

    override fun onProductClick(productId: ProductId) {
        onOutput(ReceiptDetailsComponent.Output.OnProductChosen(productId))
    }

    override fun onDismissClicked() {
        onOutput(ReceiptDetailsComponent.Output.OnDismissRequested)
    }
}