package com.bael.dads.data.database.test.di.module

import android.content.Context
import com.bael.dads.data.database.DadsDatabase
import com.bael.dads.data.database.di.module.DatabaseModule
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import com.bael.dads.data.database.DadsDatabase.Companion.Schema as DadsTestSchema

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
internal object DatabaseTestModule {

    /**
     * SqlDelight current version doesn't support in-memory database for
     * Android's instrumentation testing requirement.
     *
     * Workaround is to create persistent database as production one.
     */
    @Provides
    @Singleton
    fun provideDadsDatabase(@ApplicationContext context: Context): DadsDatabase {
        val driver = AndroidSqliteDriver(schema = DadsTestSchema, context, name = "dads_test.db")
        return DadsDatabase(driver)
    }
}
