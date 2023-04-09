package com.cashew.core.storage.providers

import com.cashew.core.authorization.RefreshToken

interface RefreshTokenProvider {

    suspend fun getRefreshToken(): RefreshToken?

}