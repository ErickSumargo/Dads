package com.bael.dads.lib.remote.di.module.service

import com.bael.dads.lib.remote.service.DadsApolloService
import com.bael.dads.lib.remote.service.DadsService
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
interface ServiceModule {

    @Binds
    @Singleton
    fun bindDadsService(service: DadsApolloService): DadsService
}
