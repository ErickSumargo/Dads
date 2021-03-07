package com.bael.dads.lib.api.test.di.module.service

import com.bael.dads.lib.api.di.module.service.ServiceModule
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.api.test.service.DadsTestableService
import com.bael.dads.lib.api.test.service.fake.DadsFakeService
import dagger.Binds
import dagger.Module
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
internal interface ServiceModule {

    @Binds
    @Singleton
    fun bindDadsService(service: DadsFakeService): DadsTestableService<DadJokesResponse>
}
