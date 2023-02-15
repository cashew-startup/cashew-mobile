package com.cashew.core

import com.cashew.core.network.authorization.AccessTokenProvider
import com.cashew.core.network.authorization.RefreshTokenProvider
import com.cashew.features.authorization_flow.data.TokenStorageImpl
import com.cashew.features.authorization_flow.data.storages.AccessTokenStorage
import com.cashew.features.authorization_flow.data.storages.RefreshTokenStorage
import org.koin.dsl.module

val androidModule = module {
    single { TokenStorageImpl(get()) }
    single<AccessTokenStorage> { get<TokenStorageImpl>() }
    single<AccessTokenProvider> { get<TokenStorageImpl>() }
    single<RefreshTokenStorage> { get<TokenStorageImpl>() }
    single<RefreshTokenProvider> { get<TokenStorageImpl>() }
}