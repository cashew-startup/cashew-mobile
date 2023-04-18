package com.cashew.android

import com.cashew.core.coreModule
import com.cashew.features.authorization_flow.authorizationModule
import com.cashew.features.profile.profileModule

val modules = listOf(
    coreModule,
    authorizationModule,
    profileModule
)