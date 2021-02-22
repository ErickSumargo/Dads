package com.bael.dads.lib.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseWorker constructor(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    @Inject
    internal lateinit var workManager: WorkManager

    protected fun cancelWork() {
        workManager.cancelWorkById(id)
    }
}
