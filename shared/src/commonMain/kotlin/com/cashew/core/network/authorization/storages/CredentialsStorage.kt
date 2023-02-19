package com.cashew.core.network.authorization.storages

import com.cashew.core.network.authorization.*
import com.cashew.core.network.authorization.providers.CredentialsProvider

interface CredentialsStorage: CredentialsProvider {

    suspend fun saveCredentials(credentials: Credentials)

    suspend fun saveUserId(userId: UserId)

    suspend fun saveUsername(username: Username)

    suspend fun savePassword(password: Password)

}