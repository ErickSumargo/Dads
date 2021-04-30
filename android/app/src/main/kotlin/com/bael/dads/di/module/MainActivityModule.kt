package com.bael.dads.di.module

import com.bael.dads.activity.MainActivity
import com.bael.dads.library.presentation.di.qualifier.ActivityNameQualifier
import com.bael.dads.library.presentation.di.qualifier.ActivityNameQualifier.Companion.ACTIVITY_MAIN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by stef_ang on 24/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal class MainActivityModule {

    @Provides
    @Singleton
    @ActivityNameQualifier(name = ACTIVITY_MAIN)
    fun provideMainActivityName(): String {
        return MainActivity::class.java.name
    }
}
