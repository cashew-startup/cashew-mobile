package com.cashew.features.welcome

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.welcome.ui.RealWelcomeComponent
import com.cashew.features.welcome.ui.WelcomeComponent

fun ComponentFactory.createWelcomeComponent(
    componentContext: ComponentContext,
    onOutput: (WelcomeComponent.Output) -> Unit
): WelcomeComponent {
    return RealWelcomeComponent(componentContext, onOutput)
}