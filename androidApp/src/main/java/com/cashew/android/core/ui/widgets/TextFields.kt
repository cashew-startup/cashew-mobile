package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cashew.android.R
import com.cashew.android.core.theme.CashewTheme
import kotlin.math.max
import kotlin.math.sin

@Composable
fun CashewTextField(
    hint: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    isEnabled: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = CashewTheme.colors.elem.secondary,
    backgroundDisabledColor: Color = CashewTheme.colors.elem.secondary,
    backgroundErrorColor: Color = CashewTheme.colors.elem.error,
    contentColor: Color = CashewTheme.colors.text.primary,
    contentDisabledColor: Color = CashewTheme.colors.text.primary,
    contentErrorColor: Color = CashewTheme.colors.text.error,
    strokeColor: Color = CashewTheme.colors.elem.stroke,
    strokeErrorColor: Color = CashewTheme.colors.elem.strokeError,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    radius: Dp = 5.dp
) {

    val currentBackgroundColor = when {
        isError -> backgroundErrorColor
        isEnabled -> backgroundColor
        else -> backgroundDisabledColor
    }

    val currentTextColor = when {
        isError -> contentErrorColor
        isEnabled -> contentColor
        else -> contentDisabledColor
    }

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        placeholder = {
            Text(text = hint)
        },
        enabled = isEnabled,
        textStyle = CashewTheme.typography.text.regular,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = currentBackgroundColor,
            textColor = currentTextColor,
            leadingIconColor = contentColor,
            trailingIconColor = contentColor,
            disabledTextColor = currentTextColor,
            disabledLeadingIconColor = contentDisabledColor,
            disabledTrailingIconColor = contentDisabledColor,
            focusedBorderColor = strokeColor,
            unfocusedBorderColor = strokeColor,
            errorBorderColor = strokeErrorColor,
            errorLeadingIconColor = contentErrorColor,
            errorTrailingIconColor = contentErrorColor,
        ),
        shape = RoundedCornerShape(radius)
    )
}

@Composable
fun PrimaryTextField(
    hint: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    isEnabled: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    radius: Dp = 5.dp
) {
    CashewTextField(
        hint = hint,
        onTextChange = onTextChange,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        radius = radius
    )
}

@Composable
fun SearchTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isError: Boolean = false,
) {
    PrimaryTextField(
        hint = stringResource(id = R.string.search),
        onTextChange = onTextChange,
        modifier = modifier,
        isEnabled = isEnabled,
        singleLine = true,
        maxLines = 1,
        radius = 15.dp,
        isError = isError
    )
}