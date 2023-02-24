package com.cashew.core.message.data

import com.cashew.core.message.domain.Message
import kotlinx.coroutines.flow.Flow

interface MessageService {

    val messageFlow: Flow<Message>

    suspend fun showMessage(message: Message)

}