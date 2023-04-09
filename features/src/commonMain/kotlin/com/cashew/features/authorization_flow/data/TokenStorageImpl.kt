package com.cashew.features.authorization_flow.data

import com.cashew.core.authorization.AccessToken
import com.cashew.core.authorization.RefreshToken
import com.cashew.core.storage.storages.AccessTokenStorage
import com.cashew.core.storage.storages.RefreshTokenStorage
import com.russhwolf.settings.Settings

class TokenStorageImpl(
    private val settings: Settings
) : AccessTokenStorage, RefreshTokenStorage {

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        settings.putString(KEY_ACCESS_TOKEN, accessToken.value)
    }

    override suspend fun getAccessToken(): AccessToken? {
        val accessTokenString = settings.getStringOrNull(KEY_ACCESS_TOKEN) ?: return null
        return AccessToken(accessTokenString)
    }

    override suspend fun saveRefreshToken(refreshToken: RefreshToken) {
        settings.putString(KEY_REFRESH_TOKEN, refreshToken.value)
    }

    override suspend fun getRefreshToken(): RefreshToken? {
        val refreshTokenString = settings.getStringOrNull(KEY_REFRESH_TOKEN) ?: return null
        return RefreshToken(refreshTokenString)
    }

    private companion object {
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
    }

}