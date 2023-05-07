package com.cashew.features.friends

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.friends.data.FriendsRepository
import com.cashew.features.friends.ui.FriendsComponent
import com.cashew.features.friends.ui.RealFriendsComponent
import com.cashew.features.friends.ui.list.FriendsListComponent
import com.cashew.features.friends.ui.list.RealFriendsListComponent
import org.koin.core.component.get

fun ComponentFactory.createFriendsListComponent(
    componentContext: ComponentContext
): FriendsListComponent {
    val friendsReplica = get<FriendsRepository>().friendsReplica
    return RealFriendsListComponent(componentContext, friendsReplica)
}

fun ComponentFactory.createFriendsComponent(
    componentContext: ComponentContext
): FriendsComponent {
    return RealFriendsComponent(componentContext, get())
}