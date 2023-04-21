package com.cashew.android.core.ui.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cashew.android.core.painter
import com.cashew.android.core.theme.CashewTheme
import com.cashew.features.MR

@Composable
fun NavigationalBottomBar() {
    BottomNavigation() {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36Stats.painter(),
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
                    painter = MR.assets.Ic36List.painter(),
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
                    painter = MR.assets.Ic36QR.painter(),
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
                    painter = MR.assets.Ic36Group.painter(),
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
                    painter = MR.assets.Ic36Profile.painter(),
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