package com.bael.dads.lib.worker.di.entry

import androidx.hilt.work.HiltWorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface EntryPoint {

    fun accessWorkerFactory(): HiltWorkerFactory
}
