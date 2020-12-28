package com.bael.lib.database.di.module

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.bael.lib.database.SampleDatabase
import com.bael.lib.database.SampleRoomDatabase
import com.bael.lib.database.di.qualifier.DatabaseNameQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    @DatabaseNameQualifier
    internal fun provideDatabaseName(): String = "sample"

    @Provides
    @Singleton
    internal fun provideDatabase(
        @ApplicationContext context: Context,
        @DatabaseNameQualifier databaseName: String
    ): SampleDatabase {
        return databaseBuilder(context, SampleRoomDatabase::class.java, databaseName).build()
    }
}
