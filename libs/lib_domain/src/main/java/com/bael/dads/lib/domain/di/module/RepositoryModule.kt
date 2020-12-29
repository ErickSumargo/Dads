package com.bael.dads.lib.domain.di.module

import com.bael.dads.lib.domain.repository.DadsRepository
import com.bael.dads.lib.domain.repository.DefaultDadsRepository
import dagger.Binds
import dagger.Module
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
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindDadsRepository(
        repository: DefaultDadsRepository
    ): DadsRepository
}
