package com.cashew.features.profile.data

import com.cashew.features.profile.domain.Profile
import me.aartikov.replica.single.Replica

interface ProfileRepository {

    val profileReplica: Replica<Profile>

}