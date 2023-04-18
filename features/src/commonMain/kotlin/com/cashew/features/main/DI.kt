package com.cashew.features.main

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.main.ui.MainComponent
import com.cashew.features.main.ui.RealMainComponent
import org.koin.core.component.get

fun ComponentFactory.createMainComponent(
    componentContext: ComponentContext
): MainComponent {
    return RealMainComponent(componentContext, get())
}