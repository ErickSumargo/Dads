package com.bael.dads.lib.threading.test.di.module

import com.bael.dads.lib.threading.Thread
import com.bael.dads.lib.threading.di.module.ThreadingModule
import com.bael.dads.lib.threading.test.FakeThread
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ThreadingModule::class]
)
internal interface ThreadingTestModule {

    @Binds
    @Singleton
    fun bindThread(thread: FakeThread): Thread
}
