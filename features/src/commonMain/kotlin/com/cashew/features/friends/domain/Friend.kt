package com.cashew.features.friends.domain

data class Friend(
    val id: String,
    val name: String
) {
    companion object {
        fun mock(index: Int) = Friend(
            id = index.toString(),
            name = "Friend $index"
        )

        fun mocks() = (0..5).map { mock(it) }
    }
}
