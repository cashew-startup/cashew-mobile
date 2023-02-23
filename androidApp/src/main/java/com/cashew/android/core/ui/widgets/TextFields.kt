package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.CashewTheme
import com.cashew.core.MR

@Composable
fun CashewTextField(
    placeholder: String,
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
    shape: Shape = RoundedCornerShape(5.dp),
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    contentPadding: PaddingValues = PaddingValues(
        vertical = 10.dp,
        horizontal = 15.dp
    )
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

    val colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
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
        placeholderColor = currentTextColor
    )

    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
        value = text,
        modifier = modifier
            .background(colors.backgroundColor(isEnabled).value, shape),
        onValueChange = onTextChange,
        enabled = isEnabled,
        textStyle = CashewTheme.typography.text.regular,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = text,
                innerTextField = innerTextField,
                placeholder = {
                    Text(text = placeholder)
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = isEnabled,
                isError = isError,
                colors = colors,
                interactionSource = interactionSource,
                visualTransformation = visualTransformation,
                contentPadding = contentPadding,
                border = {
                    TextFieldDefaults.BorderBox(
                        isEnabled,
                        isError,
                        interactionSource,
                        colors,
                        shape
                    )
                }
            )
        }
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = RoundedCornerShape(5.dp),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    CashewTextField(
        placeholder = hint,
        onTextChange = onTextChange,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = shape,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun SearchTextField(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    PrimaryTextField(
        hint = MR.strings.search.resolve(),
        onTextChange = onTextChange,
        modifier = modifier,
        isEnabled = isEnabled,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(15.dp),
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}