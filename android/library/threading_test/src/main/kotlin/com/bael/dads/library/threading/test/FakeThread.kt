package com.bael.dads.library.threading.test

import com.bael.dads.library.threading.Thread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class FakeThread @Inject constructor() : Thread {
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override val main: CoroutineDispatcher
        get() = testDispatcher

    override val default: CoroutineDispatcher
        get() = testDispatcher

    override val io: CoroutineDispatcher
        get() = testDispatcher

    override fun reset() {
        testDispatcher.cleanupTestCoroutines()
    }
}
