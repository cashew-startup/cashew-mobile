package com.cashew.android.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

@Composable
fun StringDesc.resolve(): String = this.toString(LocalContext.current)

@Composable
fun StringResource.resolve(): String = desc().resolve()

