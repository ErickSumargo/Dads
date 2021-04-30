@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.data.remote.test.di.module.service

import com.bael.dads.data.remote.di.module.service.ServiceModule
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.data.remote.service.DadsService
import com.bael.dads.data.remote.test.service.FakeDadsService
import com.bael.dads.data.remote.test.service.RemoteService
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
internal object ServiceTestModule {

    @Provides
    @Singleton
    fun provideDadsService(): DadsService {
        return FakeDadsService()
    }

    @Provides
    @Singleton
    fun provideFakeDadsService(service: DadsService): RemoteService<DadJokesResponse> {
        return service as RemoteService<DadJokesResponse>
    }
}
