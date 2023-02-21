package com.cashew.core.storage.providers

import com.cashew.core.network.authorization.Credentials
import com.cashew.core.network.authorization.Password
import com.cashew.core.network.authorization.UserId
import com.cashew.core.network.authorization.Username

interface CredentialsProvider {

    suspend fun getCredentials(): Credentials?

    suspend fun getUserId(): UserId?

    suspend fun getUsername(): Username?

    suspend fun getPassword(): Password?

}