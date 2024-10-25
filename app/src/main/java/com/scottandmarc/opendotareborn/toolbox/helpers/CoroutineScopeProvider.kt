package com.scottandmarc.opendotareborn.toolbox.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
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