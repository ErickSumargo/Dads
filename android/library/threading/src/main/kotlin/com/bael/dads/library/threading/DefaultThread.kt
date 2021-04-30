package com.bael.dads.library.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

class DefaultThread @Inject constructor() : Thread {
    override val main: CoroutineDispatcher
        get() = Main

    override val default: CoroutineDispatcher
        get() = Default

    override val io: CoroutineDispatcher
        get() = IO

    override fun reset() {}
}
