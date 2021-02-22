package com.bael.dads.lib.domain.di.module.repository

import com.bael.dads.lib.domain.repository.DadsRepository
import com.bael.dads.lib.domain.repository.DefaultDadsRepository
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
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDadsRepository(repository: DefaultDadsRepository): DadsRepository
}
