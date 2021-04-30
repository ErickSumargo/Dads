package com.bael.dads.library.threading.di.module

import com.bael.dads.library.threading.DefaultThread
import com.bael.dads.library.threading.Thread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
interface ThreadingModule {

    @Binds
    @Singleton
    fun bindThread(thread: DefaultThread): Thread
}
