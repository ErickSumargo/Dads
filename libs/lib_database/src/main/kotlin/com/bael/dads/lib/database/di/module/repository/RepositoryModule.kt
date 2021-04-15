package com.bael.dads.lib.database.di.module.repository

import com.bael.dads.lib.database.repository.DadsRepository
import com.bael.dads.lib.database.repository.DefaultDadsRepository
import com.bael.dads.lib.database.repository.DefaultRemoteMetaRepository
import com.bael.dads.lib.database.repository.RemoteMetaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDadsRepository(repository: DefaultDadsRepository): DadsRepository

    @Binds
    @Singleton
    fun bindRemoteMetaRepository(repository: DefaultRemoteMetaRepository): RemoteMetaRepository
}
