package com.cashew.features.authorization_flow.data

import com.cashew.features.authorization_flow.data.dto.LoginRequestDTO
import com.cashew.features.authorization_flow.data.dto.LoginResponseDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AccountRepositoryImpl(
    private val httpClient: HttpClient,
) : AccountRepository {

    override suspend fun login(username: String, password: String): LoginResponseDTO {
        return httpClient.post("/auth/login") {
            setBody(LoginRequestDTO(username, password))
        }.body()
    }
}