package com.cashew.features.profile.data.dto

import com.cashew.core.authorization.UserId
import com.cashew.core.authorization.Username
import com.cashew.features.profile.domain.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponseDTO(
    @SerialName("id") val id: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String
)

fun ProfileResponseDTO.toDomain() = Profile(
    id = UserId(id),
    username = Username(username),
    email = email
)