package com.cashew.features.profile.domain

import com.cashew.core.authorization.UserId
import com.cashew.core.authorization.Username

data class Profile(
    val username: Username,
    val email: String
) {
    companion object {
        fun mock() = Profile(
            username = Username("kursor"),
            email = "kursor"
        )
    }
}