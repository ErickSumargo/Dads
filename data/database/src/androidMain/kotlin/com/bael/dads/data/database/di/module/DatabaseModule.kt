package com.bael.dads.data.database.di.module

import android.content.Context
import com.bael.dads.data.database.DadsDatabase
import com.bael.dads.data.database.constant.Database.name
import com.bael.dads.data.database.constant.Database.schema
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDadsDatabase(@ApplicationContext context: Context): DadsDatabase {
        val driver = AndroidSqliteDriver(schema, context, name)
        return DadsDatabase(driver)
    }
}
