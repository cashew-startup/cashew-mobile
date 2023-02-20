package com.cashew.core


import com.cashew.core.network.HttpClientProvider
import com.cashew.core.network.authorization.TokenRefresher
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.network.exceptions.ExceptionMapper
import com.cashew.core.network.requests.RequestHandler
import me.aartikov.replica.client.ReplicaClient
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider(Const.BACKEND_URL, get(), get(), get()) }
    single { TokenRefresher(get(), get(), get()) }
    single { RequestHandler(get(), get()) }
    single { ExceptionMapper() }
    single { ExceptionHandler(get()) }
    single { ReplicaClient() }
}