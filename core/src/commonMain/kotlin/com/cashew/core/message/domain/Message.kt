package com.cashew.core.message.domain

import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.desc.StringDesc

data class Message(
    val text: StringDesc,
    val iconRes: AssetResource? = null,
    val action: MessageAction? = null
)

data class MessageAction(
    val title: StringDesc,
    val action: () -> Unit
)
