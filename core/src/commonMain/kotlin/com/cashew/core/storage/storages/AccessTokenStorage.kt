package com.cashew.core.storage.storages

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.storage.providers.AccessTokenProvider

interface AccessTokenStorage : AccessTokenProvider {

    suspend fun saveAccessToken(accessToken: AccessToken)

}