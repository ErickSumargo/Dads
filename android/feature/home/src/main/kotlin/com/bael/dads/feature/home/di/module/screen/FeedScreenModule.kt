package com.bael.dads.feature.home.di.module.screen

import com.bael.dads.feature.home.screen.feed.DefaultRendererInitializer
import com.bael.dads.feature.home.screen.feed.Renderer
import com.bael.dads.feature.home.screen.feed.State
import com.bael.dads.feature.home.screen.feed.ViewModel
import com.bael.dads.library.presentation.renderer.RendererInitializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(value = [ViewModelComponent::class, FragmentComponent::class])
internal interface FeedScreenModule {

    @Binds
    fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State {
            return State(responses = listOf())
        }
    }
}
