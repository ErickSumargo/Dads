package com.bael.dads.library.instrumentation.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.testing.TestListenableWorkerBuilder
import com.bael.dads.library.instrumentation.ui.BaseUITest
import com.bael.dads.library.threading.Thread
import com.bael.dads.library.worker.BaseWorker
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Created by ErickSumargo on 15/05/21.
 */

abstract class BaseWorkerTest<WF : WorkerFactory> : BaseUITest() {
    @get:Rule
    internal val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @Inject
    protected lateinit var workManager: WorkManager

    @Inject
    protected lateinit var workerFactory: WF

    @Inject
    internal lateinit var thread: Thread

    val context: Context
        get() = getApplicationContext()

    @Before
    internal fun setup() {
        hiltRule.inject()
        setupTest()
    }

    abstract fun setupTest()

    protected inline fun <reified W : BaseWorker> createWorker(inputData: Data? = null): W {
        val workerBuilder = TestListenableWorkerBuilder<W>(context)
        workerBuilder.setWorkerFactory(workerFactory)

        inputData?.also { data ->
            workerBuilder.setInputData(data)
        }

        return workerBuilder.build().apply {
            workManager = this@BaseWorkerTest.workManager
        }
    }

    protected fun runTest(test: suspend TestCoroutineScope.() -> Unit) {
        (thread.default as TestCoroutineDispatcher).runBlockingTest(test)
    }

    @After
    internal fun tearDown() {
        thread.reset()
        clearTest()
    }

    abstract fun clearTest()
}
