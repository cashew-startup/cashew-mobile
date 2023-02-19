package com.cashew.android.core.ui.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cashew.android.R
import com.cashew.android.core.theme.CashewTheme

@Composable
fun NavigationalBottomBar() {
    BottomNavigation() {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.stats),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = CashewTheme.colors.icons.primary
                )
            },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = CashewTheme.colors.icons.primary
                )
            },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.qr),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = Color.Unspecified
                )
            },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = CashewTheme.colors.icons.primary
                )
            },
            selected = false,
            onClick = { /*TODO*/ },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier,
                    tint = CashewTheme.colors.icons.primary
                )
            },
            selected = false,
            onClick = { /*TODO*/ },
        )
    }
}