package com.cashew.core.network.authorization.storages

import com.cashew.core.network.authorization.RefreshToken
import com.cashew.core.network.authorization.providers.RefreshTokenProvider

interface RefreshTokenStorage : RefreshTokenProvider {

    suspend fun saveRefreshToken(refreshToken: RefreshToken)

}