package com.bael.dads.lib.api.di.module.network

import com.bael.dads.lib.api.network.DefaultNetwork
import com.bael.dads.lib.api.network.Network
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
internal interface NetworkModule {

    @Binds
    @Singleton
    fun bindNetwork(network: DefaultNetwork): Network
}
