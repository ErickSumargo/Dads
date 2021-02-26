package com.bael.dads.feature.home.di.module.sheet

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bael.dads.feature.home.sheet.sharepreview.DefaultRendererInitializer
import com.bael.dads.feature.home.sheet.sharepreview.Renderer
import com.bael.dads.feature.home.sheet.sharepreview.State
import com.bael.dads.feature.home.sheet.sharepreview.ViewModel
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
internal interface SharePreviewSheetModule {

    @Binds
    fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State {
            return State(dadJoke = null)
        }

        @Provides
        fun Fragment.provideViewModel(): Lazy<ViewModel> {
            return viewModels()
        }
    }
}
