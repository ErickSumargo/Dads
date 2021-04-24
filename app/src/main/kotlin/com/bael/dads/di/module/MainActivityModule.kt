package com.bael.dads.di.module

import com.bael.dads.activity.MainActivity
import com.bael.dads.lib.presentation.di.ActivityNameQualifier
import com.bael.dads.lib.presentation.di.ActivityNameQualifier.Companion.ACTIVITY_MAIN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainActivityModule {

    @Provides
    @Singleton
    @ActivityNameQualifier(ACTIVITY_MAIN)
    fun provideMainActivityName(): String {
        return MainActivity::class.java.name
    }
}
