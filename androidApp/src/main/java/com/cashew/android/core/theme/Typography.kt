package com.cashew.android.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Typography as MaterialTypography

data class Typography(
    val button: ButtonTypography,
    val text: TextTypography,
    val title: TitleTypography
)

data class ButtonTypography(
    val bold: TextStyle
)

data class TextTypography(
    val regular: TextStyle
)

data class TitleTypography(
    val bold: TextStyle
)

fun Typography.toMaterialTypography() = MaterialTypography(
    h2 = title.bold,
    body1 = text.regular,
    body2 = button.bold
)

val LocalCashewTypography = staticCompositionLocalOf<Typography?> { null }