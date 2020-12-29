package com.bael.dads.feature.home.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bael.dads.feature.home.screen.highlight.State
import com.bael.dads.feature.home.screen.highlight.ViewModel
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
object HighlightScreenModule {

    @Provides
    fun provideState(): State = State()

    @Provides
    fun Fragment.provideViewModel(): Lazy<ViewModel> = viewModels()
}
