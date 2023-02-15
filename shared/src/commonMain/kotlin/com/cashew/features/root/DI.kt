package com.cashew.features.root

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.root.ui.RealRootComponent
import com.cashew.features.root.ui.RootComponent
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(
    componentContext: ComponentContext
) : RootComponent {
    return RealRootComponent(componentContext, get())
}