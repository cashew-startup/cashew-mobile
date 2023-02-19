package com.cashew.features.authorization_flow

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.core.network.HttpClientProvider
import com.cashew.features.authorization_flow.data.AuthorizationRepository
import com.cashew.features.authorization_flow.data.AuthorizationRepositoryImpl
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent
import com.cashew.features.authorization_flow.ui.RealAuthorizationFlowComponent
import com.cashew.features.authorization_flow.ui.login.AuthorizationLoginComponent
import com.cashew.features.authorization_flow.ui.login.RealAuthorizationLoginComponent
import com.cashew.features.authorization_flow.ui.register.AuthorizationRegisterComponent
import com.cashew.features.authorization_flow.ui.register.RealAuthorizationRegisterComponent
import org.koin.core.component.get
import org.koin.dsl.module

val authorizationModule = module {
    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl(get(), get(), get())
    }
}

fun ComponentFactory.createAuthorizationFlowComponent(
    componentContext: ComponentContext,
    onOutput: (AuthorizationFlowComponent.Output) -> Unit
): AuthorizationFlowComponent {
    return RealAuthorizationFlowComponent(componentContext, onOutput, get())
}

fun ComponentFactory.createAuthorizationLoginComponent(
    componentContext: ComponentContext,
    onOutput: (AuthorizationLoginComponent.Output) -> Unit
): AuthorizationLoginComponent {
    return RealAuthorizationLoginComponent(componentContext, onOutput, get())
}

fun ComponentFactory.createAuthorizationRegisterComponent(
    componentContext: ComponentContext,
    onOutput: (AuthorizationRegisterComponent.Output) -> Unit
): AuthorizationRegisterComponent {
    return RealAuthorizationRegisterComponent(componentContext, onOutput, get())
}

