package com.cashew

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform