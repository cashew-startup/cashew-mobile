package com.cashew.features.profile.ui

import com.cashew.core.wrappers.CStateFlow
import com.cashew.features.profile.domain.Profile
import me.aartikov.replica.single.Loadable

interface ProfileComponent {

    val profile: CStateFlow<Loadable<Profile>>

    val mode: CStateFlow<Mode>

    sealed interface Mode {
        object EditingProfile : Mode
        object EditingPassword : Mode
        object Viewing : Mode
    }

}