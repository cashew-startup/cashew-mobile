package com.cashew.features.authorization_flow.data

interface AuthorizationRepository {

    suspend fun login(username: String, password: String): LoginResult

    suspend fun register(username: String, password: String): RegisterResult

    sealed interface LoginResult {
        object Ok : LoginResult
        object InvalidCredentials : LoginResult
    }

    sealed interface RegisterResult {
        object Ok : RegisterResult
        object UserAlreadyExists : RegisterResult
    }
}