package com.bael.dads.lib.presentation.test.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bael.dads.lib.presentation.fragment.BaseFragment
import com.bael.dads.lib.threading.Thread
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseFragmentTest {
    @get:Rule
    internal val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    internal val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    internal lateinit var thread: Thread

    @Before
    internal fun setup() {
        hiltRule.inject()
        setupTest()
    }

    abstract fun setupTest()

    protected inline fun <reified F : BaseFragment<*, *, *, *>> launch(
        crossinline test: (F) -> Unit = {}
    ) {
        launchFragmentInHiltContainer<F> {
            test(this as F)
        }
    }

    protected fun runTest(test: suspend TestCoroutineScope.() -> Unit) {
        (thread.main as TestCoroutineDispatcher).runBlockingTest(test)
    }

    @After
    internal fun tearDown() {
        thread.reset()
        clearTest()
    }

    abstract fun clearTest()
}
