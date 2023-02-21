package com.cashew.core.network.authorization.storages

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.providers.AccessTokenProvider

interface AccessTokenStorage : AccessTokenProvider {

    suspend fun saveAccessToken(accessToken: AccessToken)

}