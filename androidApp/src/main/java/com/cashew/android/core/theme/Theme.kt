package com.cashew.android.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkAppColors else LightAppColors
    val typography = AppTypography
    CashewTheme(colors, typography) {
        MaterialTheme(
            colors = colors.toMaterialColors(),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}

@Composable
fun CashewTheme(
    colors: Colors,
    typography: Typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCashewColors provides colors,
        LocalCashewTypography provides typography
    ) {
        content()
    }
}

object CashewTheme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalCashewColors.current ?: throw Exception("Theme was not applied (Colors not provided)")

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalCashewTypography.current ?: throw Exception("Theme was not applied (Typography not provided)")

}