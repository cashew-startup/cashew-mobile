package com.cashew.features.receipt.ui.details

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Product
import com.cashew.features.receipt.domain.ProductId
import com.cashew.features.receipt.domain.Receipt

interface ReceiptDetailsComponent {

    val searchTextState: CStateFlow<String>

    val originalReceipt: CStateFlow<Receipt>

    val nameState: CStateFlow<String>

    val allPayers: CStateFlow<List<Friend>>

    val productsState: CStateFlow<List<ProductViewData>>

    fun onNameChanged(name: String)

    fun onSearch(text: String)

    fun onSaveClick()

    fun onProductClick(productId: ProductId)

    fun onDismissClicked()

    sealed interface Output {
        object OnDismissRequested : Output
        object OnReceiptSaved : Output

        data class OnProductChosen(val productId: ProductId) : Output
    }

}