package com.cashew.android.core.theme

import androidx.compose.ui.graphics.Color

val LightAppColors = Colors(
    isLight = true,
    background = Background(
        primary = Color(0xFFFFFFFF),
        secondary = Color(0xFFC1C1C1),
        stroke = Color(0xFF56008A)
    ),
    elem = Elem(
        primary = Color(0xFFEEE1FE),
        secondary = Color(0xFFF8F1FF)
    ),
    text = Text(
        primary = Color(0xFF56008A),
        contrast = Color(0xFFFFFFFF)
    ),
    button = Button(
        primary = Color(0xFF00CF15),
        stroke = Color(0xFF005508)
    ),
    icons = Icons(
        primary = Color(0xFF56008A)
    )
)

val DarkAppColors = LightAppColors // not yet implemented

val AppTypography = Typography()