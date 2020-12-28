package com.bael.lib.domain.di.module

import com.bael.lib.domain.interactor.DefaultSampleInteractor
import com.bael.lib.domain.interactor.SampleInteractor
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
internal abstract class InteractorModule {

    @Binds
    @Singleton
    internal abstract fun bindSampleInteractor(
        interactor: DefaultSampleInteractor
    ): SampleInteractor
}
