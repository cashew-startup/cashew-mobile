package com.cashew.features.parties.data.dto

import com.cashew.core.authorization.Username
import com.cashew.features.friends.domain.Friend
import com.cashew.features.parties.domain.Party
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PartiesResponseDTO(
    @SerialName("parties") val parties: List<PartiesResponseItem>
)

@Serializable
class PartiesResponseItem(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("ownerId") val ownerId: Long,
    @SerialName("ownerUsername") val ownerUsername: String,
    @SerialName("date") val date: String,
    @SerialName("users") val memberList: List<String>,
)

fun PartiesResponseDTO.toDomain() = parties.map { it ->
    Party(
        id = it.id,
        name = it.name,
        ownerId = it.ownerId,
        ownerUsername = Username(it.ownerUsername),
        date = it.date,
        memberList = it.memberList.map { Friend(it) }
    )
}