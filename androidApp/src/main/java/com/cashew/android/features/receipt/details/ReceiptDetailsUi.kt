package com.cashew.android.features.receipt.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashew.android.core.theme.AppTheme
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.friends.domain.Friend
import com.cashew.features.receipt.domain.Product
import com.cashew.features.receipt.domain.Receipt
import com.cashew.features.receipt.ui.details.ProductViewData
import com.cashew.features.receipt.ui.details.ReceiptDetailsComponent
import com.cashew.features.receipt.ui.details.toViewData

@Composable
fun ReceiptDetailsUi(
    component: ReceiptDetailsComponent,
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun ReceiptDetailsUiPreview() {
    AppTheme {

    }
}

class FakeReceiptDetailsComponent : ReceiptDetailsComponent {

    override val originalReceipt: Receipt = Receipt.mock(0)
    override val nameState: CStateFlow<String> = CMutableStateFlow(originalReceipt.name)
    override val allPayers: CStateFlow<List<Friend>> = CMutableStateFlow(Friend.mocks())
    override val productsState: CStateFlow<List<ProductViewData>> = CMutableStateFlow(
        Product.mocks().map { it.toViewData(allPayers.value) }
    )
    override fun onNameChanged(name: String) = Unit
}