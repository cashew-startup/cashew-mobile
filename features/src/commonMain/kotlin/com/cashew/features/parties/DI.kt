package com.cashew.features.parties

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.ComponentFactory
import com.cashew.features.parties.data.PartiesRepository
import com.cashew.features.parties.ui.PartiesComponent
import com.cashew.features.parties.ui.RealPartiesComponent
import com.cashew.features.parties.ui.list.PartiesListComponent
import com.cashew.features.parties.ui.list.RealPartiesListComponent
import org.koin.core.component.get

fun ComponentFactory.createPartiesListComponent(
    componentContext: ComponentContext
): PartiesListComponent {
    val partiesReplica = get<PartiesRepository>().partiesReplica
    return RealPartiesListComponent(componentContext, partiesReplica)
}

fun ComponentFactory.createPartiesComponent(
    componentContext: ComponentContext
): PartiesComponent {
    return RealPartiesComponent(componentContext, get())
}
