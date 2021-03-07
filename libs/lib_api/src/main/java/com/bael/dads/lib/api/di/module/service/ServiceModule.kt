package com.bael.dads.lib.api.di.module.service

import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.api.service.apollo.DadsApolloService
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
