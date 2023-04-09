package com.cashew.core.authorization

import kotlin.jvm.JvmInline

@JvmInline
value class Password(val value: String)

@JvmInline
value class Username(val value: String)
@JvmInline
value class UserId(val value: String)

data class Credentials(
    val userId: UserId,
    val username: Username,
    val password: Password
)