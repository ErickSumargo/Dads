package com.bael.dads.lib.preference.test.di.module

import com.bael.dads.lib.preference.Preference
import com.bael.dads.lib.preference.di.module.PreferenceModule
import com.bael.dads.lib.preference.test.FakePreference
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PreferenceModule::class]
)
internal interface PreferenceTestModule {

    @Binds
    @Singleton
    fun bindPreference(preference: FakePreference): Preference
}
