package com.bael.dads.library.worker.di.entry

import androidx.hilt.work.HiltWorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by ErickSumargo on 01/01/21.
 */

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface EntryPoint {

    fun accessWorkerFactory(): HiltWorkerFactory
}
