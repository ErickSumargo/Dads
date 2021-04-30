package com.bael.dads.library.instrumentation.sheet

import android.content.Context
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.bael.dads.library.instrumentation.fragment.launchFragmentInHiltContainer
import com.bael.dads.library.instrumentation.ui.BaseUITest
import com.bael.dads.library.presentation.sheet.BaseSheet
import com.bael.dads.library.threading.Thread
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
 * Created by ErickSumargo on 15/04/21.
 */

abstract class BaseSheetTest : BaseUITest() {
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

    protected inline fun <reified F : BaseSheet<*, *, *, *>> launch(
        args: Bundle? = null,
        @NavigationRes graphResId: Int = -1,
        crossinline test: (F) -> Unit = {}
    ) {
        launchFragmentInHiltContainer<F>(context, args, graphResId) {
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
