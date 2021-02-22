package com.bael.dads.lib.worker.initializer

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.Configuration.Provider
import androidx.work.WorkManager
import com.bael.dads.lib.worker.di.entry.EntryPoint
import dagger.hilt.android.EntryPointAccessors

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class WorkManagerInitializer : Initializer<WorkManager>, Provider {
    private lateinit var workerFactory: HiltWorkerFactory

    override fun create(context: Context): WorkManager {
        initializeDependencies(context)
        initializeWorkManager(context)

        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    private fun initializeDependencies(context: Context) {
        val entry = EntryPointAccessors.fromApplication(context, EntryPoint::class.java)
        workerFactory = entry.accessWorkerFactory()
    }

    private fun initializeWorkManager(context: Context) {
        WorkManager.initialize(context, workManagerConfiguration)
    }
}
