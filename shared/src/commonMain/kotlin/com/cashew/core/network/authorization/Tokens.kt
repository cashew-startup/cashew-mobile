package com.cashew.core.network.authorization

@JvmInline
value class AccessToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)


interface AccessTokenProvider {

    suspend fun getAccessToken(): AccessToken?

}

interface RefreshTokenProvider {

    suspend fun getRefreshToken(): RefreshToken?

}