package com.cashew.core

import com.cashew.core.network.HttpClientProvider
import com.cashew.core.network.authorization.TokenRefresher
import com.cashew.core.network.exceptions.ExceptionMapper
import com.cashew.core.network.exceptions.NetworkExceptionHandler
import com.cashew.core.network.requests.RequestHandler
import com.cashew.core.utils.DefaultNetworkCoroutineContext
import com.kursor.kmmdataloadingautomation.LoaderClient
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider(Const.BACKEND_URL, get(), get(), get()) }
    single { TokenRefresher(get(), get(), get()) }
    single { RequestHandler(get(), get()) }
    single { ExceptionMapper() }
    single { NetworkExceptionHandler() }
    single { LoaderClient(CoroutineScope(DefaultNetworkCoroutineContext)) }
}