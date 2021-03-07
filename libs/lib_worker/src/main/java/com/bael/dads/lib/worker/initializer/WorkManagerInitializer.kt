package com.bael.dads.lib.worker.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.Configuration.Provider
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.bael.dads.lib.worker.di.entry.EntryPoint
import com.bael.dads.lib.worker.factory.NoOpWorkerFactory
import dagger.hilt.android.EntryPointAccessors

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class WorkManagerInitializer : Initializer<WorkManager>, Provider {
    private lateinit var workerFactory: WorkerFactory

    override fun create(context: Context): WorkManager {
        initializeWorkerFactory(context)
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

    private fun initializeWorkerFactory(context: Context) {
        workerFactory = try {
            EntryPointAccessors
                .fromApplication(context, EntryPoint::class.java)
                .accessWorkerFactory()
        } catch (cause: Exception) {
            /**
             * "androidx.startup.StartupException:
             * java.lang.IllegalStateException: The component was not created.
             * Check that you have added the HiltAndroidRule"
             *
             * Waiting for the fix around HiltTestApplication scope,
             * in the meantime we substitute the worker factory with dummy test double.
             */
            NoOpWorkerFactory()
        }
    }

    private fun initializeWorkManager(context: Context) {
        WorkManager.initialize(context, workManagerConfiguration)
    }
}
