package com.bael.dads.library.instrumentation.di.module

import com.bael.dads.library.instrumentation.activity.MainTestActivity
import com.bael.dads.library.presentation.di.qualifier.ActivityNameQualifier
import com.bael.dads.library.presentation.di.qualifier.ActivityNameQualifier.Companion.ACTIVITY_MAIN
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
internal class MainTestActivityModule {

    @Provides
    @Singleton
    @ActivityNameQualifier(name = ACTIVITY_MAIN)
    fun provideMainTestActivityName(): String {
        return MainTestActivity::class.java.name
    }
}
