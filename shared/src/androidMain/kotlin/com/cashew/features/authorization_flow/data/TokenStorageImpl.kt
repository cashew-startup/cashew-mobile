package com.cashew.features.authorization_flow.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme
import androidx.security.crypto.MasterKey
import com.cashew.core.network.authorization.AccessToken
import com.cashew.core.network.authorization.RefreshToken
import com.cashew.features.authorization_flow.data.storages.AccessTokenStorage
import com.cashew.features.authorization_flow.data.storages.RefreshTokenStorage

internal class TokenStorageImpl(
    context: Context
) : AccessTokenStorage, RefreshTokenStorage {

    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "access_token_shared_pref",
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        encryptedSharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken.value).apply()
    }

    override suspend fun getAccessToken(): AccessToken? {
        val tokenString = encryptedSharedPreferences.getString(KEY_ACCESS_TOKEN, null) ?: return null
        return AccessToken(tokenString)
    }

    override suspend fun saveRefreshToken(refreshToken: RefreshToken) {
        encryptedSharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken.value).apply()
    }

    override suspend fun getRefreshToken(): RefreshToken? {
        val tokenString = encryptedSharedPreferences.getString(KEY_REFRESH_TOKEN, null) ?: return null
        return RefreshToken(tokenString)
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
    }
}