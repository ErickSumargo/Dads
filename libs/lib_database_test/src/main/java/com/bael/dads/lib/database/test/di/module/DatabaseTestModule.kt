package com.bael.dads.lib.database.test.di.module

import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.di.module.DatabaseModule
import com.bael.dads.lib.database.test.DadsFakeDatabase
import com.bael.dads.lib.database.test.DadsTestableDatabase
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
    replaces = [DatabaseModule::class]
)
internal interface DatabaseTestModule {

    @Binds
    @Singleton
    fun bindDatabase(database: DadsFakeDatabase): DadsDatabase

    companion object {

        @Provides
        fun provideTestableDatabase(database: DadsDatabase): DadsTestableDatabase {
            return database as DadsTestableDatabase
        }
    }
}
