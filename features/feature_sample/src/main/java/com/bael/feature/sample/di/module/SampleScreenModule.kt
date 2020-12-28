package com.bael.dads.feature.sample.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bael.dads.feature.sample.screen.sample.State
import com.bael.dads.feature.sample.screen.sample.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(value = [ApplicationComponent::class, FragmentComponent::class])
@ExperimentalCoroutinesApi
object SampleScreenModule {

    @Provides
    fun provideState(): State = State(version = 1)

    @Provides
    fun Fragment.provideViewModel(): Lazy<ViewModel> = viewModels()
}
