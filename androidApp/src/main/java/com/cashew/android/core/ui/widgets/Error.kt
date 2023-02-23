package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cashew.android.core.painter
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.features.MR

@Composable
fun Error(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = MR.assets.IcError24.painter(),
            contentDescription = null,
            tint = CashewTheme.colors.icons.error,
            modifier = Modifier.padding(
                end = 7.dp
            )
        )

        Text(
            text = text,
            style = CashewTheme.typography.caption.light,
            color = CashewTheme.colors.text.error,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }
}


@Preview
@Composable
fun ErrorPreview() {
    AppTheme {
        Error(text = "Error")
    }
}

@Preview
@Composable
fun ErrorLongPreview() {
    AppTheme {
        Error(text = "Error Long Error Long Error Long Error Long Error Long Error Long")
    }
}