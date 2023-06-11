package com.cashew.features.friends.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class FriendId(val value: Long) : Parcelable

data class Friend(
    val id: FriendId,
    val name: String
) {
    companion object {
        fun mock(index: Int) = Friend(
            id = FriendId(index.toLong()),
            name = "Friend $index"
        )

        fun mocks() = (0..5).map { mock(it) }
    }
}
