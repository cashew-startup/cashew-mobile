package com.cashew.android

import com.cashew.core.coreModule
import com.cashew.features.authorization_flow.authorizationModule
import com.cashew.features.friends.friendsModule
import com.cashew.features.profile.profileModule
import com.cashew.features.receipt.receiptModule

val modules = listOf(
    coreModule,
    authorizationModule,
    profileModule,
    friendsModule,
    receiptModule
)