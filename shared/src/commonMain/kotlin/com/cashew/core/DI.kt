package com.cashew.core

import com.cashew.core.network.HttpClientProvider
import com.cashew.core.utils.DefaultNetworkCoroutineContext
import com.kursor.kmmdataloadingautomation.LoaderClient
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider(Const.BACKEND_URL, get()) }
    single { LoaderClient(CoroutineScope(DefaultNetworkCoroutineContext)) }
}