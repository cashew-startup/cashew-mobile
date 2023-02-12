package com.cashew.android.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors as MaterialColors

data class Colors(
    val isLight: Boolean,
    val background: Background,
    val elem: Elem,
    val text: Text,
    val button: Button,
    val icons: Icons
)

data class Background(
    val primary: Color,
    val secondary: Color,
    val stroke: Color
)

data class Elem(
    val primary: Color,
    val secondary: Color
)

data class Text(
    val primary: Color,
    val contrast: Color
)

data class Button(
    val primary: Color,
    val stroke: Color
)

data class Icons(
    val primary: Color
)

fun Colors.toMaterialColors(): MaterialColors = MaterialColors(
    isLight = isLight,
    primary = background.primary,
    primaryVariant = background.secondary,
    secondary = background.primary,
    secondaryVariant = background.secondary,
    background = background.primary,
    surface = elem.primary,
    error = button.primary,
    onPrimary = text.primary,
    onSecondary = text.primary,
    onBackground = text.primary,
    onSurface = text.primary,
    onError = button.stroke
)

val LocalCashewColors = staticCompositionLocalOf<Colors?> { null }