package com.cashew.features.authorization_flow.data

import com.cashew.features.authorization_flow.data.dto.RegisterRequestDTO
import com.cashew.features.authorization_flow.data.dto.RegisterResponseDTO

interface AuthorizationRepository {

    suspend fun register(registerRequestDTO: RegisterRequestDTO): RegisterResponseDTO {
        TODO()
    }
}