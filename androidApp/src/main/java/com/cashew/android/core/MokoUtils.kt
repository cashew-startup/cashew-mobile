package com.cashew.android.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc

@Composable
fun StringResource.resolve(): String = this.desc().toString(LocalContext.current)