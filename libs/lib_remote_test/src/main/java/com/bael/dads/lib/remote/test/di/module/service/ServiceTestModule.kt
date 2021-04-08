@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.remote.test.di.module.service

import com.bael.dads.lib.remote.di.module.service.ServiceModule
import com.bael.dads.lib.remote.response.DadJokesResponse
import com.bael.dads.lib.remote.service.DadsService
import com.bael.dads.lib.remote.test.service.FakeDadsService
import com.bael.dads.lib.remote.test.service.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
internal interface ServiceTestModule {

    @Binds
    @Singleton
    fun bindDadsService(service: FakeDadsService): DadsService

    companion object {

        @Provides
        fun provideDadsService(service: DadsService): RemoteService<DadJokesResponse> {
            return service as RemoteService<DadJokesResponse>
        }
    }
}
