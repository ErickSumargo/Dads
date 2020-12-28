package com.bael.dads.lib.api.di.module

import com.bael.dads.lib.api.service.SampleService
import com.bael.dads.lib.api.service.retrofit.SampleRetrofitService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class ServiceModule {

    @Binds
    @Singleton
    internal abstract fun bindSampleService(
        service: SampleRetrofitService
    ): SampleService
}
