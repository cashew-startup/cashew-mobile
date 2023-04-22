package com.cashew.features.friends.data.dto

import com.cashew.features.friends.domain.Friend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FriendResponseDTO(
    @SerialName("friends") val friends: List<String>
)

fun FriendResponseDTO.toDomain() = friends.map { Friend(name = it) }