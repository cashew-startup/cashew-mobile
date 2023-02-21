package com.cashew.core.utils

import com.russhwolf.settings.Settings
import org.koin.core.Koin

actual fun Koin.createSettings(name: String, encrypted: Boolean): Settings {
    TODO()
}

//private fun createRegularSettings(): Settings {
//    return NSUserDefaultsSettings(
//        delegate = NSUserDefaults.standardUserDefaults
//    )
//}
//
//private fun createEncryptedSettings(name: String): Settings {
//
//}