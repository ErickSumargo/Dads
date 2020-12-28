package com.bael.dads.lib.api.di.module

import com.bael.dads.lib.api.network.DefaultNetwork
import com.bael.dads.lib.api.network.Network
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
internal abstract class NetworkModule {

    @Binds
    @Singleton
    internal abstract fun bindNetwork(network: DefaultNetwork): Network
}
