package com.cashew.core.message.data

import com.cashew.core.message.domain.Message
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class MessageServiceImpl : MessageService {

    private val messageChannel: Channel<Message> = Channel(capacity = Channel.UNLIMITED)

    override val messageFlow: Flow<Message> = messageChannel.receiveAsFlow()

    override suspend fun showMessage(message: Message) {
        messageChannel.trySend(message)
    }
}