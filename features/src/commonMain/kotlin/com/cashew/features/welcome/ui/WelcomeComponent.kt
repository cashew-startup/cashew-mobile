package com.cashew.features.welcome.ui

interface WelcomeComponent {

    fun onGetStartedClick()

    sealed interface Output {
        object OnGetStartedRequested : Output
    }

}