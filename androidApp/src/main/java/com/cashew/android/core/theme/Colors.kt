package com.cashew.android.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors as MaterialColors

data class Colors(
    val isLight: Boolean,
    val background: BackgroundColors,
    val elem: ElemColors,
    val text: TextColors,
    val button: ButtonColors,
    val icons: IconColors
)

data class BackgroundColors(
    val primary: Color,
    val secondary: Color
)

data class ElemColors(
    val primary: Color,
    val secondary: Color,
    val stroke: Color,
    val error: Color,
    val strokeError: Color
)

data class TextColors(
    val primary: Color,
    val contrast: Color,
    val error: Color
)

data class ButtonColors(
    val primary: Color,
    val secondary: Color,
    val strokePrimary: Color,
    val strokeSecondary: Color
)

data class IconColors(
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
    error = elem.error,
    onPrimary = text.primary,
    onSecondary = text.primary,
    onBackground = text.primary,
    onSurface = text.primary,
    onError = text.error
)

val LocalCashewColors = staticCompositionLocalOf<Colors?> { null }