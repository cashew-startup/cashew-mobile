package com.cashew.android

import com.cashew.core.androidModule
import com.cashew.core.coreModule
import com.cashew.features.authorization_flow.authorizationModule

val modules = listOf(
    coreModule,
    androidModule,
    authorizationModule,

)