package com.cashew.features.authorization_flow.data

import com.cashew.core.authorization.Credentials
import com.cashew.core.authorization.Password
import com.cashew.core.authorization.UserId
import com.cashew.core.authorization.Username
import com.cashew.core.storage.storages.CredentialsStorage
import com.russhwolf.settings.Settings

class CredentialsStorageImpl(
    private val settings: Settings
) : CredentialsStorage {

    override suspend fun saveCredentials(credentials: Credentials) {
        saveUserId(credentials.userId)
        saveUsername(credentials.username)
        savePassword(credentials.password)
    }

    override suspend fun saveUserId(userId: UserId) {
        settings.putString(KEY_USER_ID, userId.value)
    }

    override suspend fun saveUsername(username: Username) {
        settings.putString(KEY_USERNAME, username.value)
    }

    override suspend fun savePassword(password: Password) {
        settings.putString(KEY_PASSWORD, password.value)
    }

    override suspend fun getCredentials(): Credentials? {
        val userId = getUserId() ?: return null
        val username = getUsername() ?: return null
        val password = getPassword() ?: return null
        return Credentials(userId, username, password)
    }

    override suspend fun getUserId(): UserId? {
        val userIdString = settings.getStringOrNull(KEY_USER_ID) ?: return null
        return UserId(userIdString)
    }

    override suspend fun getUsername(): Username? {
        val usernameString = settings.getStringOrNull(KEY_USERNAME) ?: return null
        return Username(usernameString)
    }

    override suspend fun getPassword(): Password? {
        val passwordString = settings.getStringOrNull(KEY_PASSWORD) ?: return null
        return Password(passwordString)
    }

    private companion object {
        private const val KEY_USER_ID = "key_user_id"
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
    }


}