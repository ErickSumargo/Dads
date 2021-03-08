@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.api.test.di.module.service

import com.bael.dads.lib.api.di.module.service.ServiceModule
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.api.test.service.TestableService
import com.bael.dads.lib.api.test.service.fake.DadsFakeService
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
    fun bindDadsService(service: DadsFakeService): DadsService

    companion object {

        @Provides
        fun provideDadsTestableService(
            service: DadsService
        ): TestableService<DadJokesResponse> {
            return service as TestableService<DadJokesResponse>
        }
    }
}
