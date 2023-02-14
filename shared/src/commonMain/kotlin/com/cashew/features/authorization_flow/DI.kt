package com.cashew.features.authorization_flow

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.authorization_flow.ui.AuthorizationFlowComponent
import com.cashew.features.authorization_flow.ui.RealAuthorizationFlowComponent
import org.koin.dsl.module

val authorizationModule = module {

}

fun ComponentFactory.createAuthorizationFlowComponent(
    componentContext: ComponentContext,
    onOutput: (AuthorizationFlowComponent.Output) -> Unit
): AuthorizationFlowComponent {
    return RealAuthorizationFlowComponent(componentContext, onOutput)
}

