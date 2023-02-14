package com.cashew.features.authorization_flow.data

import com.cashew.features.authorization_flow.data.dto.LoginResponseDTO

interface AccountRepository {

    suspend fun login(username: String, password: String): LoginResponseDTO
}