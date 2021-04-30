package com.bael.dads.feature.home.di.module.screen

import com.bael.dads.feature.home.screen.seen.DefaultRendererInitializer
import com.bael.dads.feature.home.screen.seen.Renderer
import com.bael.dads.feature.home.screen.seen.State
import com.bael.dads.feature.home.screen.seen.ViewModel
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
internal interface SeenScreenModule {

    @Binds
    fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State {
            return State(
                isFavoriteFilterActivated = false,
                responses = listOf()
            )
        }
    }
}
