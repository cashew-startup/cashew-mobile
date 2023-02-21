package com.cashew.core.network.authorization.providers

import com.cashew.core.network.authorization.RefreshToken

interface RefreshTokenProvider {

    suspend fun getRefreshToken(): RefreshToken?

}