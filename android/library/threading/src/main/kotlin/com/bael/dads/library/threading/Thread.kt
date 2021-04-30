package com.bael.dads.library.threading

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Thread {
    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher

    fun reset()
}
