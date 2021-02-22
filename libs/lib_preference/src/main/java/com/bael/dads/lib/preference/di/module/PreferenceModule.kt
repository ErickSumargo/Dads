package com.bael.dads.lib.preference.di.module

import com.bael.dads.lib.preference.PreferenceStore
import com.bael.dads.lib.preference.Store
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface PreferenceModule {

    @Binds
    @Singleton
    fun bindPreferenceStore(preference: PreferenceStore): Store
}
