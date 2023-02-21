package com.cashew.core.storage.providers

import com.cashew.core.network.authorization.AccessToken

interface AccessTokenProvider {

    suspend fun getAccessToken(): AccessToken?

}