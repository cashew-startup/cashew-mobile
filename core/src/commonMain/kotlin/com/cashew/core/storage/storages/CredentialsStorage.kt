package com.cashew.core.storage.storages

import com.cashew.core.network.authorization.Credentials
import com.cashew.core.network.authorization.Password
import com.cashew.core.network.authorization.UserId
import com.cashew.core.network.authorization.Username
import com.cashew.core.storage.providers.CredentialsProvider

interface CredentialsStorage: CredentialsProvider {

    suspend fun saveCredentials(credentials: Credentials)

    suspend fun saveUserId(userId: UserId)

    suspend fun saveUsername(username: Username)

    suspend fun savePassword(password: Password)

}