package com.cashew.features.welcome.ui

import com.arkivanov.decompose.ComponentContext

class RealWelcomeComponent(
    componentContext: ComponentContext,
    private val onOutput: (WelcomeComponent.Output) -> Unit
) : ComponentContext by componentContext, WelcomeComponent {

    override fun onGetStartedClick() {
        onOutput(WelcomeComponent.Output.OnGetStartedRequested)
    }
}