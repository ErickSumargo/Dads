package com.bael.dads.lib.instrumentation.fragment

import android.content.Context
import androidx.annotation.NavigationRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.bael.dads.lib.instrumentation.ui.BaseUITest
import com.bael.dads.lib.presentation.fragment.BaseFragment
import com.bael.dads.lib.threading.Thread
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseFragmentTest : BaseUITest() {
    @get:Rule
    internal val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    internal val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    internal lateinit var thread: Thread

    val context: Context
        get() = getApplicationContext()

    @Before
    internal fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(dispatcher = thread.main)

        setupTest()
    }

    abstract fun setupTest()

    protected inline fun <reified F : BaseFragment<*, *, *, *>> launch(
        @NavigationRes graphResId: Int = -1,
        crossinline test: (F) -> Unit = {}
    ) {
        launchFragmentInHiltContainer<F>(context, graphResId = graphResId) {
            test(this as F)
        }
    }

    protected fun runTest(test: suspend TestCoroutineScope.() -> Unit) {
        (thread.main as TestCoroutineDispatcher).runBlockingTest(test)
    }

    @After
    internal fun tearDown() {
        Dispatchers.resetMain()
        thread.reset()

        clearTest()
    }

    abstract fun clearTest()
}
