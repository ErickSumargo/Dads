package com.bael.dads.feature.home.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bael.dads.feature.home.screen.highlight.DefaultRendererInitializer
import com.bael.dads.feature.home.screen.highlight.Renderer
import com.bael.dads.feature.home.screen.highlight.State
import com.bael.dads.feature.home.screen.highlight.ViewModel
import com.bael.dads.lib.presentation.renderer.RendererInitializer
import dagger.Binds
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
abstract class HighlightScreenModule {

    @Binds
    abstract fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State = State()

        @Provides
        fun Fragment.provideViewModel(): Lazy<ViewModel> = viewModels()
    }
}
