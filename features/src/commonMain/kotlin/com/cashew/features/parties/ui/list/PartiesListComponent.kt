package com.cashew.features.parties.ui.list

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.parties.domain.Party
import me.aartikov.replica.single.Loadable

interface PartiesListComponent {

    val partyListState: CStateFlow<Loadable<List<Party>>>

    sealed interface Output {

    }
}