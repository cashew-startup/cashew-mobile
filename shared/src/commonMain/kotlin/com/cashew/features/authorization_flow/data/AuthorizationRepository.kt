package com.cashew.features.authorization_flow.data

import com.cashew.features.authorization_flow.domain.LoginResult
import com.cashew.features.authorization_flow.domain.RegisterResult

interface AuthorizationRepository {

    suspend fun login(username: String, password: String): LoginResult

    suspend fun register(username: String, password: String): RegisterResult
}