package com.bael.dads.lib.instrumentation.di.module

import com.bael.dads.lib.instrumentation.activity.MainTestActivity
import com.bael.dads.lib.presentation.di.ActivityNameQualifier
import com.bael.dads.lib.presentation.di.ActivityNameQualifier.Companion.ACTIVITY_MAIN
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by stef_ang on 26/04/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = []
)
class MainTestActivityModule {

    @Provides
    @Singleton
    @ActivityNameQualifier(name = ACTIVITY_MAIN)
    fun provideMainTestActivityName(): String {
        return MainTestActivity::class.java.name
    }
}
