package com.cashew.features.profile

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.core.network.HttpClientProvider
import com.cashew.features.profile.data.ProfileRepository
import com.cashew.features.profile.data.ProfileRepositoryImpl
import com.cashew.features.profile.ui.ProfileComponent
import com.cashew.features.profile.ui.RealProfileComponent
import org.koin.core.component.get
import org.koin.dsl.module

val profileModule = module {
    single<ProfileRepository> {
        ProfileRepositoryImpl(
            get<HttpClientProvider>().authorizedHttpClient,
            get(),
            get()
        )
    }
}

fun ComponentFactory.createProfileComponent(
    componentContext: ComponentContext
): ProfileComponent {
    return RealProfileComponent(
        componentContext,
        get<ProfileRepository>().profileReplica
    )
}