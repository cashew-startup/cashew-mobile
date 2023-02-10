package com.cashew.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack

interface RootComponent {

    val childStack: ChildStack<*, Child>

    sealed interface Child {

    }

}