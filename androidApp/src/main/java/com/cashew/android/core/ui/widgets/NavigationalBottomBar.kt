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
import com.cashew.features.main.ui.MainComponent

@Composable
fun NavigationalBottomBar(
    activeTab: MainComponent.Tab,
    onActiveTabChanged: (MainComponent.Tab) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36Stats.painter(),
                    contentDescription = null
                )
            },
            selected = activeTab == MainComponent.Tab.Expenses,
            onClick = { onActiveTabChanged(MainComponent.Tab.Expenses) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36List.painter(),
                    contentDescription = null
                )
            },
            selected = activeTab == MainComponent.Tab.Receipt,
            onClick = { onActiveTabChanged(MainComponent.Tab.Receipt) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36QR.painter(),
                    contentDescription = null
                )
            },
            selected = activeTab == MainComponent.Tab.Scan,
            onClick = { onActiveTabChanged(MainComponent.Tab.Scan) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36Group.painter(),
                    contentDescription = null
                )
            },
            selected = activeTab == MainComponent.Tab.Friends,
            onClick = { onActiveTabChanged(MainComponent.Tab.Friends) },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = MR.assets.Ic36Profile.painter(),
                    contentDescription = null
                )
            },
            selected = activeTab == MainComponent.Tab.Profile,
            onClick = { onActiveTabChanged(MainComponent.Tab.Profile) },
        )
    }
}