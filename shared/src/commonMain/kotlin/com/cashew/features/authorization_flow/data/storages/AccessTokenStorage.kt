package com.cashew.features.authorization_flow.data.storages

import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.AccessTokenProvider

interface AccessTokenStorage : AccessTokenProvider {

    suspend fun saveAccessToken(accessToken: AccessToken)

}