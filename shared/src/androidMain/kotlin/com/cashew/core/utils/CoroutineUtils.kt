package com.cashew.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


actual val DefaultNetworkCoroutineContext: CoroutineContext
    get() = Job() + Dispatchers.Main.immediate