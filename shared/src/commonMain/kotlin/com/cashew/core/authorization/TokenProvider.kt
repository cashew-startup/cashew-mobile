package com.cashew.core.authorization

import kotlin.jvm.JvmInline

interface TokenProvider {

    val token: Token?

}

@JvmInline
value class Token(val value: String)