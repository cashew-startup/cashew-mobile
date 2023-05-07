package com.cashew.features.parties.data

import com.cashew.features.parties.domain.Party
import me.aartikov.replica.single.Replica

interface PartiesRepository {
    val partiesReplica: Replica<List<Party>>
}