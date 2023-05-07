package com.cashew.features.parties.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.parties.ui.list.PartiesListComponent

interface PartiesComponent {

    val childStackFlow: CStateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: PartiesListComponent) : Child
    }

}