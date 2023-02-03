package com.cashew.core

import com.cashew.core.network.HttpClientProvider
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider(Const.BACKEND_URL, get()) }
}