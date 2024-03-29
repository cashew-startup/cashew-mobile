package com.cashew.android.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cashew.android.core.painter
import com.cashew.android.core.resolve
import com.cashew.android.core.theme.AppTheme
import com.cashew.android.core.theme.CashewTheme
import com.cashew.android.core.ui.widgets.PrimaryButton
import com.cashew.features.MR
import com.cashew.features.welcome.ui.WelcomeComponent

@Composable
fun WelcomeUi(
    component: WelcomeComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Image(
                painter = MR.assets.BackgroundWelcomeScreen.painter(),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxSize(),
                alignment = Alignment.TopCenter
            )
            Column(modifier = Modifier.matchParentSize()) {
                Spacer(modifier = Modifier.height(82.dp))
                Text(
                    text = MR.strings.welcome_phrase_1.resolve(),
                    style = TextStyle(fontSize = 40.sp),
                    color = CashewTheme.colors.text.contrast,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
                Text(
                    text = MR.strings.welcome_phrase_2.resolve(),
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 30.dp),
                    color = CashewTheme.colors.text.contrast
                )
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(
                    text = MR.strings.welcome_get_started.resolve(),
                    onClick = component::onGetStartedClick,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 94.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomeUiPreview() {
    AppTheme {
        WelcomeUi(component = FakeWelcomeComponent())
    }
}

class FakeWelcomeComponent : WelcomeComponent {

    override fun onGetStartedClick() = Unit
}