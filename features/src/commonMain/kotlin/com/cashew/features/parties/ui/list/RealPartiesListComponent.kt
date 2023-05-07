package com.cashew.features.parties.ui.list

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.observe
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.parties.domain.Party
import me.aartikov.replica.single.Loadable
import me.aartikov.replica.single.Replica

class RealPartiesListComponent(
    componentContext: ComponentContext,
    partiesReplica: Replica<List<Party>>,
) : ComponentContext by componentContext, PartiesListComponent {
    override val partyListState: CStateFlow<Loadable<List<Party>>> = partiesReplica.observe(lifecycle)
}