package com.cashew.features.receipt.ui.details

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Receipt

interface ReceiptDetailsComponent {

    val originalReceipt: Receipt

    val nameState: CStateFlow<String>

    val allPayers: CStateFlow<List<Friend>>

    val productsState: CStateFlow<List<ProductViewData>>

    fun onNameChanged(name: String)

    

}