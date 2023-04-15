package com.cashew.features.profile.domain

import com.cashew.core.authorization.UserId
import com.cashew.core.authorization.Username

data class Profile(
    val id: UserId,
    val username: Username,
    val email: String
)