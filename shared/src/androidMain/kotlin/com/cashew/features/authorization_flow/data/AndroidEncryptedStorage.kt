package com.cashew.features.authorization_flow.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme
import androidx.security.crypto.MasterKey
import com.cashew.core.network.authorization.*
import com.cashew.core.network.authorization.storages.AccessTokenStorage
import com.cashew.core.network.authorization.storages.CredentialsStorage
import com.cashew.core.network.authorization.storages.RefreshTokenStorage

internal class AndroidEncryptedStorage(
    context: Context
) : AccessTokenStorage,
    RefreshTokenStorage,
    CredentialsStorage
{

    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "encrypted_shared_pref",
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        encryptedSharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken.value).apply()
    }

    override suspend fun getAccessToken(): AccessToken? {
        val tokenString =
            encryptedSharedPreferences.getString(KEY_ACCESS_TOKEN, null) ?: return null
        return AccessToken(tokenString)
    }

    override suspend fun saveRefreshToken(refreshToken: RefreshToken) {
        encryptedSharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken.value).apply()
    }

    override suspend fun getRefreshToken(): RefreshToken? {
        val tokenString =
            encryptedSharedPreferences.getString(KEY_REFRESH_TOKEN, null) ?: return null
        return RefreshToken(tokenString)
    }

    override suspend fun saveCredentials(credentials: Credentials) {
        saveUserId(credentials.userId)
        saveUsername(credentials.username)
        savePassword(credentials.password)
    }

    override suspend fun saveUserId(userId: UserId) {
        encryptedSharedPreferences.edit().putString(KEY_USER_ID, userId.value).apply()
    }

    override suspend fun saveUsername(username: Username) {
        encryptedSharedPreferences.edit().putString(KEY_USERNAME, username.value).apply()
    }

    override suspend fun savePassword(password: Password) {
        encryptedSharedPreferences.edit().putString(KEY_PASSWORD, password.value).apply()
    }

    override suspend fun getCredentials(): Credentials? {
        val userId = getUserId() ?: return null
        val username = getUsername() ?: return null
        val password = getPassword() ?: return null
        return Credentials(userId, username, password)
    }

    override suspend fun getUserId(): UserId? {
        val userIdString = encryptedSharedPreferences.getString(KEY_USER_ID, null) ?: return null
        return UserId(userIdString)
    }

    override suspend fun getUsername(): Username? {
        val usernameString = encryptedSharedPreferences.getString(KEY_USERNAME, null) ?: return null
        return Username(usernameString)
    }

    override suspend fun getPassword(): Password? {
        val passwordString = encryptedSharedPreferences.getString(KEY_PASSWORD, null) ?: return null
        return Password(passwordString)
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
        private const val KEY_USER_ID = "key_user_id"
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
    }
}