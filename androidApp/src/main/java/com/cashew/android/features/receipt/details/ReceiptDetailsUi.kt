package com.cashew.android.features.receipt.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.PrimaryButton
import com.cashew.android.core.ui.widgets.PrimaryTextField
import com.cashew.android.core.ui.widgets.SearchToolbar
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.MR
import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Product
import com.cashew.features.receipt.domain.ProductId
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.ui.details.ProductViewData
import com.cashew.features.receipt.ui.details.ReceiptDetailsComponent
import com.cashew.features.receipt.ui.details.toViewData

@Composable
fun ReceiptDetailsUi(
    component: ReceiptDetailsComponent,
    modifier: Modifier = Modifier
) {

    val searchText by component.searchTextState.collectAsState()
    val name by component.nameState.collectAsState()
    val products by component.productsState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchToolbar(
                text = searchText,
                onTextChanged = component::onSearch
            )
        },
        floatingActionButton = {
            PrimaryButton(
                text = MR.strings.receipt_details_button_save.resolve(),
                onClick = component::onSaveClick
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .padding(paddingValues)
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp)),
            color = CashewTheme.colors.elem.primary,
            border = BorderStroke(
                width = 1.dp,
                color = CashewTheme.colors.elem.stroke
            )
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                )
            ) {
                item {
                    PrimaryTextField(
                        text = name,
                        placeholder = MR.strings.receipt_details_placeholder_name.resolve(),
                        onTextChange = component::onNameChanged,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Text(
                        text = component.originalReceipt.date,
                        style = CashewTheme.typography.text.regular,
                        color = CashewTheme.colors.text.primary,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                item { 
                    ColumnHeaders()
                }
                items(products) {
                    ProductItem(
                        productViewData = it,
                        onProductClick = { component.onProductClick(it.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnHeaders(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = MR.strings.receipt_details_column_product.resolve(),
            style = CashewTheme.typography.text.bold,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = MR.strings.receipt_details_column_payers.resolve(),
            style = CashewTheme.typography.text.bold,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = MR.strings.receipt_details_column_cost.resolve(),
            style = CashewTheme.typography.text.bold,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun ProductItem(
    productViewData: ProductViewData,
    onProductClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .clickable(onClick = onProductClick)
    ) {
        Text(
            text = productViewData.name,
            style = CashewTheme.typography.text.regular,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = "${productViewData.payers.size}/${productViewData.allPayersCount}",
            style = CashewTheme.typography.text.regular,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = productViewData.cost.toString(),
            style = CashewTheme.typography.text.regular,
            color = CashewTheme.colors.text.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun ReceiptDetailsUiPreview() {
    AppTheme {
        ReceiptDetailsUi(component = FakeReceiptDetailsComponent())
    }
}

class FakeReceiptDetailsComponent : ReceiptDetailsComponent {
    override val searchTextState: CStateFlow<String> = CMutableStateFlow("")
    override val originalReceipt: Receipt = Receipt.mock(0)
    override val nameState: CStateFlow<String> = CMutableStateFlow(originalReceipt.name)
    override val allPayers: CStateFlow<List<Friend>> = CMutableStateFlow(Friend.mocks())
    override val productsState: CStateFlow<List<ProductViewData>> = CMutableStateFlow(
        Product.mocks().map { it.toViewData(allPayers.value) }
    )

    override fun onNameChanged(name: String) = Unit
    override fun onSearch(text: String) = Unit
    override fun onSaveClick() = Unit
    override fun onProductClick(productId: ProductId) = Unit
}