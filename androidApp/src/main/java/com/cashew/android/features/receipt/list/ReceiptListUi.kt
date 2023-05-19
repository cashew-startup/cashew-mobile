package com.cashew.android.features.receipt.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.SearchToolbar
import com.cashew.android.core.ui.widgets.SecondaryButton
import com.cashew.android.core.ui.widgets.Title
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.domain.ReceiptId
import com.cashew.features.receipt.ui.list.ReceiptListComponent

@Composable
fun ReceiptListUi(
    component: ReceiptListComponent,
    modifier: Modifier = Modifier
) {
    val searchText by component.searchText.collectAsState()
    val receiptList by component.receiptListState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchToolbar(
                text = searchText,
                onTextChanged = component::onSearch,
                onSettingsClick = component::onSettingsClick
            )
        }
    ) { paddingValues ->
        ReceiptListContent(
            receiptList = receiptList,
            onReceiptClick = component::onReceiptClick,
            onDeleteReceiptClick = component::onDeleteReceiptClick,
            onScanReceiptClick = component::onScanReceiptClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ReceiptListContent(
    receiptList: List<Receipt>,
    onReceiptClick: (ReceiptId) -> Unit,
    onDeleteReceiptClick: (ReceiptId) -> Unit,
    onScanReceiptClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (receiptList.isEmpty()) {
        EmptyReceiptContent(onScanReceiptClick = onScanReceiptClick)
    } else {
        LazyColumn(modifier = modifier) {
            items(receiptList) {
                ReceiptItem(
                    receipt = it,
                    onClick = { onReceiptClick(it.id) },
                    onDeleteReceiptClick = { onDeleteReceiptClick(it.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReceiptItem(
    receipt: Receipt,
    onClick: () -> Unit,
    onDeleteReceiptClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            width = 1.dp,
            color = CashewTheme.colors.elem.stroke
        ),
        onClick = onClick
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            Column {
                Title(
                    text = receipt.receiptNumber,
                    modifier = Modifier.padding(bottom = 34.dp)
                )
                Text(
                    text = receipt.date,
                    color = CashewTheme.colors.text.primary,
                    style = CashewTheme.typography.text.regular
                )
            }

            IconButton(
                onClick = onDeleteReceiptClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = MR.assets.Ic32Delete.painter(),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun EmptyReceiptContent(
    onScanReceiptClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = CashewTheme.colors.elem.stroke
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .size(250.dp)
                .drawBehind {
                    drawRoundRect(
                        color = borderColor,
                        cornerRadius = CornerRadius(15.dp.toPx()),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(10.dp.toPx(), 10.dp.toPx()),
                                phase = 0f
                            )
                        )
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = MR.strings.receipt_list_empty_content_text.resolve(),
                style = CashewTheme.typography.text.regular,
                color = CashewTheme.colors.text.primary,
                modifier = Modifier.padding(
                    top = 32.dp,
                    bottom = 32.dp,
                    start = 15.dp,
                    end = 15.dp
                ),
                textAlign = TextAlign.Center
            )
            SecondaryButton(
                text = MR.strings.receipt_list_empty_content_scan_button.resolve(),
                onClick = onScanReceiptClick
            )
        }
    }
}

@Preview
@Composable
fun ReceiptListUiPreview() {
    AppTheme {
        ReceiptListUi(component = FakeReceiptListComponent())
    }
}

class FakeReceiptListComponent : ReceiptListComponent {

    override val receiptListState: CStateFlow<List<Receipt>> =
        CMutableStateFlow(Receipt.mocks())
    override val searchText: CStateFlow<String> =
        CMutableStateFlow("")

    override fun onSearch(query: String) = Unit

    override fun onSettingsClick() = Unit

    override fun onScanReceiptClick() = Unit

    override fun onReceiptClick(receiptId: ReceiptId) = Unit

    override fun onDeleteReceiptClick(receiptId: ReceiptId) = Unit

    override fun onReceiptOptionsClick() = Unit
}

