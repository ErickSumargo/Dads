package com.bael.dads.lib.domain.di.module

import com.bael.dads.lib.domain.interactor.home.LoadHighlights
import com.bael.dads.lib.domain.repository.DefaultDadsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
@ExperimentalCoroutinesApi
internal object InteractorModule {

    @Provides
    @Singleton
    internal fun provideLoadHighlights(
        repository: DefaultDadsRepository
    ): LoadHighlights {
        return { limit ->
            repository.loadHighlights(limit)
        }
    }
}
