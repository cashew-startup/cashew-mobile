package com.cashew.features.receipt.ui

import com.cashew.features.receipt.ui.list.ReceiptListComponent

interface ReceiptComponent {

    sealed interface Child {
        class List(val component: ReceiptListComponent) : Child
    }

}