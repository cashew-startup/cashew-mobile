package com.cashew.features.receipt.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.receipt.ui.details.ReceiptDetailsComponent
import com.cashew.features.receipt.ui.list.ReceiptListComponent

interface ReceiptComponent {

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: ReceiptListComponent) : Child
        class Details(val component: ReceiptDetailsComponent) : Child
    }

}