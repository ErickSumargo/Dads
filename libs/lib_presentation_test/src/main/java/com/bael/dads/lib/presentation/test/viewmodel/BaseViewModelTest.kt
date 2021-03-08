package com.bael.dads.lib.presentation.test.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.threading.Thread
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseViewModelTest {
    @get:Rule
    internal val hiltRule = HiltAndroidRule(this)

    protected lateinit var savedStateHandle: SavedStateHandle

    protected lateinit var testDispatcher: TestCoroutineDispatcher

    protected val thread: Thread = object : Thread {
        override val main: CoroutineDispatcher
            get() = testDispatcher

        override val default: CoroutineDispatcher
            get() = testDispatcher

        override val io: CoroutineDispatcher
            get() = testDispatcher
    }

    @Before
    internal fun beforeEachTest() {
        hiltRule.inject()

        savedStateHandle = SavedStateHandle()
        testDispatcher = TestCoroutineDispatcher()

        Dispatchers.setMain(testDispatcher)

        setupBeforeEachTest()
    }

    abstract fun setupBeforeEachTest()

    @After
    internal fun afterEachTest() {
        clearAfterEachTest()
    }

    abstract fun clearAfterEachTest()
}
