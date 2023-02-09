package com.cashew.core.network.authorization

import kotlin.jvm.JvmInline

@JvmInline
value class AccessToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)


interface AccessTokenProvider {

    val accessToken: AccessToken?

}

interface RefreshTokenProvider {

    val refreshToken: RefreshToken?

}