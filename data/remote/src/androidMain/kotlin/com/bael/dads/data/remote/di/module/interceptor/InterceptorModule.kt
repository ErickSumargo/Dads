package com.bael.dads.data.remote.di.module.interceptor

import com.bael.dads.data.remote.interceptor.NetworkInterceptor
import com.bael.dads.data.remote.network.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/05/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object InterceptorModule {

    @Provides
    @Singleton
    fun provideNetworkInterceptor(network: Network): NetworkInterceptor {
        return NetworkInterceptor(network)
    }
}
