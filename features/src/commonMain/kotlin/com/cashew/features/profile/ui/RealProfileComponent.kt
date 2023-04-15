package com.cashew.features.profile.ui

import com.arkivanov.decompose.ComponentContext
import com.cashew.core.utils.observe
import com.cashew.core.wrappers.CMutableStateFlow
import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.profile.domain.Profile
import me.aartikov.replica.single.Replica

class RealProfileComponent(
    componentContext: ComponentContext,
    profileReplica: Replica<Profile>
) : ComponentContext by componentContext, ProfileComponent {

    override val profile = profileReplica.observe(lifecycle)
    override val mode: CMutableStateFlow<ProfileComponent.Mode> =
        CMutableStateFlow(ProfileComponent.Mode.Viewing)




}