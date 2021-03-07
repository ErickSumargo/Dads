package com.bael.dads.lib.api.test.di.module.service

import com.bael.dads.lib.api.di.module.service.ServiceModule
import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.api.test.service.fake.DadsFakeService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

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
    fun bindDadsService(service: DadsFakeService): DadsService
}
