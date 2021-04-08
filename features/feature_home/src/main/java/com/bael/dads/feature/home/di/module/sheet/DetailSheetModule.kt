package com.bael.dads.feature.home.di.module.sheet

import com.bael.dads.feature.home.sheet.detail.DefaultRendererInitializer
import com.bael.dads.feature.home.sheet.detail.Renderer
import com.bael.dads.feature.home.sheet.detail.State
import com.bael.dads.feature.home.sheet.detail.ViewModel
import com.bael.dads.lib.presentation.renderer.RendererInitializer
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
internal interface DetailSheetModule {

    @Binds
    fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State {
            return State(dadJoke = null)
        }
    }
}
