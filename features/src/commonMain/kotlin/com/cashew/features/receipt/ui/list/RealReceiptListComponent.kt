package com.cashew.features.receipt.ui.list

import com.arkivanov.decompose.ComponentContext

class RealReceiptListComponent(
    componentContext: ComponentContext,
    onOutput: (ReceiptListComponent.Output) -> Unit
) : ComponentContext by componentContext, ReceiptListComponent {



}