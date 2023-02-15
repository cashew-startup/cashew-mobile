package com.cashew.android.core.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cashew.android.R

val LightAppColors = Colors(
    isLight = true,
    background = BackgroundColors(
        primary = Color(0xFFFFFFFF),
        secondary = Color(0xFFC1C1C1)
    ),
    elem = ElemColors(
        primary = Color(0xFFEEE1FE),
        secondary = Color(0xFFF8F1FF),
        stroke = Color(0xFF56008A),
        error = Color(0xFFFFF1F1),
        strokeError = Color(0xFF8A0000)
    ),
    text = TextColors(
        primary = Color(0xFF56008A),
        contrast = Color(0xFFFFFFFF),
        error = Color(0xFF8A0000)
    ),
    button = ButtonColors(
        primary = Color(0xFF00CF15),
        secondary = Color(0xFFEEE1FE),
        strokePrimary = Color(0xFF005508),
        strokeSecondary = Color(0xFF56008A)
    ),
    icons = IconColors(
        primary = Color(0xFF56008A),
        secondary = Color(0xFFAA80C5),
        contrast = Color(0XFFFFFFFF),
        error = Color(0xFF8A0000)
    )
)

val DarkAppColors = LightAppColors // not yet implemented

val openSansFamily = FontFamily(
    Font(R.font.open_sans_bold, weight = FontWeight.Bold),
    Font(R.font.open_sans_regular, weight = FontWeight.Normal)
)

val AppTypography = Typography(
    button = ButtonTypography(
        bold = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = openSansFamily
        )
    ),
    text = TextTypography(
        regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = openSansFamily
        )
    ),
    title = TitleTypography(
        bold = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = openSansFamily
        )
    )
)

