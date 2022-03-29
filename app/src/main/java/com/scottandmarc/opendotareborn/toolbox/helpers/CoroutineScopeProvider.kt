package com.scottandmarc.opendotareborn.toolbox.helpers

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineScopeProvider(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    private val job: Job = SupervisorJob()
) {
    private val scope: CoroutineScope = CoroutineScope(coroutineContext + job)

    fun provide() = scope

    fun cancel() = scope.cancel()

    fun cancelChildren() = job.cancelChildren()
}