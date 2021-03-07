package com.bael.dads.lib.database.test.di.module

import com.bael.dads.lib.database.di.module.DatabaseModule
import com.bael.dads.lib.database.test.DadsTestableDatabase
import com.bael.dads.lib.database.test.DadsFakeDatabase
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
    replaces = [DatabaseModule::class]
)
internal interface DatabaseModule {

    @Binds
    @Singleton
    fun bindDatabase(database: DadsFakeDatabase): DadsTestableDatabase
}
