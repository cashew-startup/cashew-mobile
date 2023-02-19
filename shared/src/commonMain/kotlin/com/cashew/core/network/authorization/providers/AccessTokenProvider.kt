package com.cashew.core.network.authorization.providers

import com.cashew.core.network.authorization.AccessToken

interface AccessTokenProvider {

    suspend fun getAccessToken(): AccessToken?

}