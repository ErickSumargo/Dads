package com.bael.dads.lib.instrumentation.di.module

import com.bael.dads.lib.instrumentation.activity.MainTestActivity
import com.bael.dads.lib.presentation.di.ActivityNameQualifier
import com.bael.dads.lib.presentation.di.ActivityNameQualifier.Companion.ACTIVITY_MAIN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by stef_ang on 26/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
class MainTestActivityModule {

    @Provides
    @Singleton
    @ActivityNameQualifier(name = ACTIVITY_MAIN)
    fun provideMainActivityName(): String {
        return MainTestActivity::class.java.name
    }
}
