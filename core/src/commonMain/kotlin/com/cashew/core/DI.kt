package com.cashew.core


import com.arkivanov.decompose.ComponentContext
import com.cashew.core.message.data.MessageService
import com.cashew.core.message.data.MessageServiceImpl
import com.cashew.core.message.ui.MessageComponent
import com.cashew.core.message.ui.RealMessageComponent
import com.cashew.core.network.HttpClientProvider
import com.cashew.core.authorization.TokenRefresher
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.network.exceptions.ExceptionMapper
import me.aartikov.replica.client.ReplicaClient
import org.koin.core.component.get
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider(Const.BACKEND_URL, get(), get(), get(), get()) }
    single { TokenRefresher(get(), get(), get()) }
    single { ExceptionMapper() }
    single { ExceptionHandler(get()) }
    single { ReplicaClient() }
    single<MessageService> { MessageServiceImpl() }
}


fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get(), get())
}