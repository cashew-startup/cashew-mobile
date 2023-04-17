package com.cashew.android.core.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cashew.core.MR

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
        error = Color(0xFF8A0000),
        delete = Color(0xFFFF2222)
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
        error = Color(0xFF8A0000),
        delete = Color(0xFFFF2222)
    )
)

val DarkAppColors = LightAppColors // not yet implemented

val openSansFamily = FontFamily(
    Font(MR.fonts.OpenSans.bold.fontResourceId, weight = FontWeight.Bold),
    Font(MR.fonts.OpenSans.regular.fontResourceId, weight = FontWeight.Normal),
    Font(MR.fonts.OpenSans.light.fontResourceId, weight = FontWeight.Light),
    Font(MR.fonts.OpenSans.medium.fontResourceId, weight = FontWeight.Medium),
    Font(MR.fonts.OpenSans.extraBold.fontResourceId, weight = FontWeight.ExtraBold),
    Font(MR.fonts.OpenSans.boldItalic.fontResourceId, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(MR.fonts.OpenSans.mediumItalic.fontResourceId, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(MR.fonts.OpenSans.lightItalic.fontResourceId, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(MR.fonts.OpenSans.italic.fontResourceId, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(MR.fonts.OpenSans.extraBoldItalic.fontResourceId, weight = FontWeight.ExtraBold, style = FontStyle.Italic)
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
        ),
        bold = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = openSansFamily
        )
    ),
    title = TitleTypography(
        semiBold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            fontFamily = openSansFamily
        )
    ),
    caption = CaptionTypography(
        light = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        )
    )
)