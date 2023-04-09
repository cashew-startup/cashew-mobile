package com.cashew.features.receipt

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.receipt.ui.RealReceiptComponent
import com.cashew.features.receipt.ui.ReceiptComponent
import org.koin.dsl.module

val receiptModule = module {

}

fun ComponentFactory.createReceiptComponent(
    componentContext: ComponentContext
): ReceiptComponent {
    return RealReceiptComponent(componentContext);
}