package com.cashew.features.authorization_flow.data.storages

import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.RefreshTokenProvider

interface RefreshTokenStorage : RefreshTokenProvider {

    suspend fun saveRefreshToken(refreshToken: RefreshToken)

}