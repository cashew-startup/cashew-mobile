package com.cashew.features.parties.domain

import com.cashew.core.authorization.Username
import com.cashew.features.friends.domain.Friend

data class Party (
    val id: Long,
    val name: String,
    val ownerId: Long,
    val ownerUsername: Username,
    val date: String,
    val memberList: List<Friend>
        ) {
}