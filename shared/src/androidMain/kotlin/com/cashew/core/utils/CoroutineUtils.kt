package com.cashew.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


actual val DefaultNetworkCoroutineContext: CoroutineContext
    get() = SupervisorJob() + Dispatchers.IO