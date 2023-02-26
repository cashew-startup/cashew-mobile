package com.cashew.android.core.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cashew.android.core.resolve
import com.cashew.core.MR
import com.cashew.core.network.exceptions.errorMessage
import me.aartikov.replica.single.Loadable

@Composable
fun <T : Any> ReplicaLceWidget(
    loadable: Loadable<T>,
    loadingContent: @Composable () -> Unit = { CircularProgressIndicator() },
    errorContent: @Composable (Exception) -> Unit = { ErrorContent(it) },
    content: @Composable (T) -> Unit
) {
    val loading = loadable.loading
    val data = loadable.data
    val exception = loadable.error?.exception

    when {
        data != null -> content(data)
        loading -> loadingContent()
        exception != null -> errorContent(exception)
    }
}

@Composable
fun ErrorContent(
    exception: Exception,
    modifier: Modifier = Modifier.fillMaxSize(),
    onRetryClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = exception.errorMessage.resolve())
        onRetryClick?.let {
            PrimaryButton(text = MR.strings.retry.resolve(), onClick = it)
        }
    }
}