package com.cashew.features.authorization_flow.data

interface AuthorizationRepository {

    suspend fun login(username: String, password: String)

    suspend fun register(username: String, password: String)
}