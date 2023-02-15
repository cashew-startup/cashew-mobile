package com.cashew.android.core.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.cashew.android.core.theme.CashewTheme

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = CashewTheme.colors.text.primary
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = CashewTheme.typography.title.bold,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}