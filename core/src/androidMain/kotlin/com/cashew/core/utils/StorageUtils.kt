package com.cashew.core.utils


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.Koin

actual fun Koin.createSettings(name: String, encrypted: Boolean): Settings {
    return if (encrypted) createEncryptedSettings(name, get())
    else createRegularSettings(name, get())
}

private fun createRegularSettings(name: String, context: Context): Settings {
    return SharedPreferencesSettings(
        delegate = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    )
}

private fun createEncryptedSettings(name: String, context: Context): Settings {
    return SharedPreferencesSettings(
        delegate = createEncryptedSharedPrefs(name, context)
    )
}

private fun createEncryptedSharedPrefs(
    name: String,
    context: Context
) = EncryptedSharedPreferences.create(
    context,
    name,
    MasterKey
        .Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build(),
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)