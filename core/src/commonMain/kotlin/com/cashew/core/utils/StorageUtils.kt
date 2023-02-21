package com.cashew.core.utils

import com.russhwolf.settings.Settings
import org.koin.core.Koin

expect fun Koin.createSettings(name: String, encrypted: Boolean): Settings