package com.cashew.core.message.ui

import com.cashew.core.message.domain.Message
import kotlinx.coroutines.flow.StateFlow

interface MessageComponent {

    val visibleMessagesState: StateFlow<List<Message>>

    fun showMessage(message: Message)

    fun onActionClick(message: Message)

}