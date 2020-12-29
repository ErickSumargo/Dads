package com.bael.dads.lib.domain.di.module

import com.bael.dads.lib.domain.interactor.DefaultLoadHighlightsInteractor
import com.bael.dads.lib.domain.interactor.LoadHighlightsInteractor
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
    internal abstract fun bindLoadHighlightsInteractor(
        interactor: DefaultLoadHighlightsInteractor
    ): LoadHighlightsInteractor
}
