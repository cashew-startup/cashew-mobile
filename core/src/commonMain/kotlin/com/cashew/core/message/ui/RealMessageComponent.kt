package com.cashew.core.message.ui

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.MR
import com.cashew.core.message.data.MessageService
import com.cashew.core.message.domain.Message
import com.cashew.core.network.exceptions.ExceptionHandler
import com.cashew.core.network.exceptions.errorMessage
import com.cashew.core.utils.componentCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val MESSAGE_SHOWING_TIME = 4000L
private const val MESSAGE_BUFFER = 3

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService,
    private val exceptionHandler: ExceptionHandler
) : ComponentContext by componentContext, MessageComponent {

    private val coroutineScope = componentCoroutineScope()

    override val visibleMessagesState: MutableStateFlow<List<Message>> =
        MutableStateFlow(emptyList())

    init {
        coroutineScope.launch {
            messageService.messageFlow.collect {
                showMessage(it)
            }
        }
        coroutineScope.launch {
            exceptionHandler.exceptionFlow.collect {
                showMessage(
                    Message(
                        text = it.errorMessage,
                        iconRes = MR.assets.IcError24
                    )
                )
            }
        }
    }

    override fun onActionClick(message: Message) {
        message.action?.action?.invoke()
        visibleMessagesState.value -= message
    }

    override fun showMessage(message: Message) {
        coroutineScope.launch {
                if (visibleMessagesState.value.size >= MESSAGE_BUFFER) {
                    visibleMessagesState.value -= visibleMessagesState.value.first()
                }
                visibleMessagesState.value += message
                delay(MESSAGE_SHOWING_TIME)
                visibleMessagesState.value -= message
        }
    }
}