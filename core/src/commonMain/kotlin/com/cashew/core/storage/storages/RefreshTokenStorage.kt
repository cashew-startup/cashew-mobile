package com.cashew.core.storage.storages

import com.cashew.core.authorization.RefreshToken
import com.cashew.core.storage.providers.RefreshTokenProvider

interface RefreshTokenStorage : RefreshTokenProvider {

    suspend fun saveRefreshToken(refreshToken: RefreshToken)

}