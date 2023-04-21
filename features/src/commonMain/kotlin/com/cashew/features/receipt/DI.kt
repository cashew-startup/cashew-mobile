package com.cashew.features.receipt

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.receipt.ui.RealReceiptComponent
import com.cashew.features.receipt.ui.ReceiptComponent
import com.cashew.features.receipt.ui.list.RealReceiptListComponent
import com.cashew.features.receipt.ui.list.ReceiptListComponent
import org.koin.core.component.get
import org.koin.dsl.module

val receiptModule = module {

}

fun ComponentFactory.createReceiptComponent(
    componentContext: ComponentContext
): ReceiptComponent {
    return RealReceiptComponent(componentContext, get())
}

fun ComponentFactory.createReceiptListComponent(
    componentContext: ComponentContext,
    onOutput: (ReceiptListComponent.Output) -> Unit
): ReceiptListComponent {
    return RealReceiptListComponent(componentContext, onOutput)
}