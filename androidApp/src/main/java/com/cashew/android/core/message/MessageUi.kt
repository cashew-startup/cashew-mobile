package com.cashew.android.core.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.core.message.domain.Message
import com.cashew.core.message.domain.MessageAction
import com.cashew.core.message.ui.MessageComponent
import com.cashew.features.MR
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun MessageUi(
    component: MessageComponent,
    modifier: Modifier = Modifier
) {

    val visibleMessages by component.visibleMessagesState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            items(visibleMessages) {
                Message(
                    text = it.text,
                    onActionClick = { component.onActionClick(it) },
                    actionTitle = it.action?.title,
                    iconRes = it.iconRes
                )
            }
        }
    }
}

@Composable
private fun Message(
    text: StringDesc,
    onActionClick: () -> Unit,
    actionTitle: StringDesc? = null,
    iconRes: AssetResource? = null,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(4.dp),
        backgroundColor = CashewTheme.colors.background.primary,
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
            .wrapContentSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconRes?.let {
                Icon(
                    painter = it.painter(),
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Text(
                text = text.resolve(),
                style = CashewTheme.typography.text.regular,
                color = CashewTheme.colors.text.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            )

            actionTitle?.let {
                TextButton(
                    onClick = onActionClick
                ) {
                    Text(
                        text = actionTitle.resolve(),
                        style = CashewTheme.typography.text.regular,
                        color = CashewTheme.colors.text.error
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MessageUiPreview() {
    AppTheme {
        MessageUi(component = FakeMessageComponent())
    }
}

class FakeMessageComponent : MessageComponent {

    val coroutineScope = CoroutineScope(Dispatchers.Main)

    override val visibleMessagesState: MutableStateFlow<List<Message>> = MutableStateFlow(
        listOf(
            Message(
                text = StringDesc.Raw("Message"),
                iconRes = MR.assets.IcError24,
                action = MessageAction(
                    title = StringDesc.Raw("Action"),
                    action = {}
                )
            )
        )
    )

    init {
        coroutineScope.launch {
            delay(2000L)
            visibleMessagesState.value += Message(StringDesc.Raw("Message 2"))
        }
    }

    override fun showMessage(message: Message) = Unit
    override fun onActionClick(message: Message) = Unit
}