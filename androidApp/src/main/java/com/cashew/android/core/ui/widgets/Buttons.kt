package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.cashew.android.core.theme.CashewTheme

@Composable
fun CashewButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CashewTheme.colors.button.primary,
    backgroundDisabledColor: Color = CashewTheme.colors.button.primary,
    strokeColor: Color = CashewTheme.colors.button.strokePrimary,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(5.dp),
    contentPadding: PaddingValues = PaddingValues(
        vertical = 10.dp,
        horizontal = 15.dp
    ),
    content: @Composable RowScope.() -> Unit
) {

    val currentBackgroundColor = if (isEnabled) backgroundColor else backgroundDisabledColor

    OutlinedButton(
        border = BorderStroke(
            width = 1.dp,
            color = strokeColor
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = currentBackgroundColor
        ),
        shape = shape,
        modifier = modifier,
        onClick = {
            if (isEnabled) onClick()
        },
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CashewTheme.colors.button.primary,
    backgroundDisabledColor: Color = CashewTheme.colors.button.primary,
    strokeColor: Color = CashewTheme.colors.button.strokePrimary,
    textColor: Color = CashewTheme.colors.text.contrast,
    textDisabledColor: Color = CashewTheme.colors.text.contrast,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(5.dp)
) {

    val currentTextColor = if (isEnabled) textColor else textDisabledColor

    CashewButton(
        onClick = onClick,
        modifier = modifier,
        backgroundColor = backgroundColor,
        backgroundDisabledColor = backgroundDisabledColor,
        strokeColor = strokeColor,
        isEnabled = isEnabled,
        shape = shape
    ) {
        Text(
            text = text,
            color = currentTextColor,
            style = CashewTheme.typography.button.bold
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CashewTheme.colors.button.secondary,
    backgroundDisabledColor: Color = CashewTheme.colors.button.secondary,
    strokeColor: Color = CashewTheme.colors.button.strokeSecondary,
    textColor: Color = CashewTheme.colors.text.primary,
    textDisabledColor: Color = CashewTheme.colors.text.primary,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(15.dp)
) {
    PrimaryButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        backgroundColor = backgroundColor,
        backgroundDisabledColor = backgroundDisabledColor,
        strokeColor = strokeColor,
        textColor = textColor,
        textDisabledColor = textDisabledColor,
        isEnabled = isEnabled,
        shape = shape
    )
}