package com.cashew.android.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.ui.widgets.NavigationalBottomBar
import com.cashew.features.main.ui.MainComponent

@Composable
fun MainUi(
    component: MainComponent,
    modifier:  Modifier = Modifier
)
{
    Scaffold(
        modifier = modifier,
        bottomBar = { NavigationalBottomBar() }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}


@Preview
@Composable
fun MainUiPreview() {
    AppTheme() {
        MainUi(component = FakeMainComponent())
    }
}

class FakeMainComponent: MainComponent {

}