package com.cashew.features.main.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.profile.ui.ProfileComponent
import com.cashew.features.receipt.ui.ReceiptComponent

interface MainComponent {

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    val activeTabState: CStateFlow<Tab>

    fun onActiveTabChanged(tab: Tab)

    sealed interface Tab {
        object Expenses : Tab
        object Receipt : Tab
        object Scan : Tab
        object Friends : Tab
        object Profile : Tab
    }

    sealed interface Child {
        class Profile(val component: ProfileComponent) : Child
        class Receipt(val component: ReceiptComponent) : Child
    }

}