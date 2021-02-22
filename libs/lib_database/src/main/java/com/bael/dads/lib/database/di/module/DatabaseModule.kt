package com.bael.dads.lib.database.di.module

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.DadsRoomDatabase
import com.bael.dads.lib.database.di.qualifier.DatabaseNameQualifier
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
internal object DatabaseModule {

    @Provides
    @Singleton
    @DatabaseNameQualifier
    fun provideDatabaseName(): String = "dads"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @DatabaseNameQualifier databaseName: String
    ): DadsDatabase {
        return databaseBuilder(context, DadsRoomDatabase::class.java, databaseName).build()
    }
}
