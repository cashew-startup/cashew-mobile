package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.theme.AppTheme
import com.cashew.features.MR

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

@Composable
fun SearchToolbar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSettingsClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier
    ) {
        SearchTextField(
            text = text,
            onTextChange = onTextChanged,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
        )
        onSettingsClick?.let {
            IconButton(
                onClick = it,
            ) {
                Icon(
                    painter = MR.assets.Ic32Settings.painter(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 20.dp
                        )
                )
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