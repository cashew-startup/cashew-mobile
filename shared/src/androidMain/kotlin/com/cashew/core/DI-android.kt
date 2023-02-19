package com.cashew.core

import com.cashew.core.network.authorization.providers.AccessTokenProvider
import com.cashew.core.network.authorization.providers.CredentialsProvider
import com.cashew.core.network.authorization.providers.RefreshTokenProvider
import com.cashew.core.network.authorization.storages.AccessTokenStorage
import com.cashew.core.network.authorization.storages.CredentialsStorage
import com.cashew.core.network.authorization.storages.RefreshTokenStorage
import com.cashew.features.authorization_flow.data.AndroidEncryptedStorage
import org.koin.dsl.module

val androidModule = module {
    single { AndroidEncryptedStorage(get()) }
    single<AccessTokenStorage> { get<AndroidEncryptedStorage>() }
    single<AccessTokenProvider> { get<AndroidEncryptedStorage>() }
    single<RefreshTokenStorage> { get<AndroidEncryptedStorage>() }
    single<RefreshTokenProvider> { get<AndroidEncryptedStorage>() }
    single<CredentialsProvider> { get<AndroidEncryptedStorage>() }
    single<CredentialsStorage> { get<AndroidEncryptedStorage>() }
}