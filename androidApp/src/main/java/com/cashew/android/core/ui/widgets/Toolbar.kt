package com.cashew.android.core.ui.widgets

import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.theme.AppTheme

@Composable
fun Toolbar(
    title: String = "",
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    onActionButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp
    ) {
        navigationIcon?.let {
            IconButton(onClick = { onNavigationIconClick?.invoke() }) {
                it()
            }
        }

        Title(text = title)

        actionIcon?.let {
            IconButton(onClick = { onActionButtonClick?.invoke() }) {
                it()
            }
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    AppTheme {
        Toolbar(
            title = "Title"
        )
    }
}