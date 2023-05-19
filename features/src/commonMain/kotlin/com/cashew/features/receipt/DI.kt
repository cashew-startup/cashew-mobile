package com.cashew.features.receipt

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.friends.data.FriendsRepository
import com.cashew.features.receipt.data.ReceiptRepository
import com.cashew.features.receipt.domain.ReceiptId
import com.cashew.features.receipt.ui.RealReceiptComponent
import com.cashew.features.receipt.ui.ReceiptComponent
import com.cashew.features.receipt.ui.details.RealReceiptDetailsComponent
import com.cashew.features.receipt.ui.details.ReceiptDetailsComponent
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

fun ComponentFactory.createReceiptDetailsComponent(
    componentContext: ComponentContext,
    receiptId: ReceiptId,
    onOutput: (ReceiptDetailsComponent.Output) -> Unit
): ReceiptDetailsComponent {
    return RealReceiptDetailsComponent(
        componentContext = componentContext,
        receiptId = receiptId,
        get<FriendsRepository>().friendsReplica,
        get<ReceiptRepository>().receiptsReplica,
        get(),
        onOutput
    )
}
