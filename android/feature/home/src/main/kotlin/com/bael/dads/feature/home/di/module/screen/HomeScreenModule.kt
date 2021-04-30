package com.bael.dads.feature.home.di.module.screen

import androidx.fragment.app.Fragment
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.screen.home.DefaultRendererInitializer
import com.bael.dads.feature.home.screen.home.Renderer
import com.bael.dads.feature.home.screen.home.State
import com.bael.dads.feature.home.screen.home.ViewModel
import com.bael.dads.library.presentation.ext.readDrawable
import com.bael.dads.library.presentation.ext.readText
import com.bael.dads.library.presentation.renderer.RendererInitializer
import com.bael.dads.library.presentation.widget.tab.data.BottomTab
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import com.bael.dads.feature.home.screen.feed.UI as FeedScreen
import com.bael.dads.feature.home.screen.seen.UI as SeenScreen

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(value = [ViewModelComponent::class, FragmentComponent::class])
internal interface HomeScreenModule {

    @Binds
    fun bindRendererInitializer(
        rendererInitializer: DefaultRendererInitializer
    ): RendererInitializer<Renderer, ViewModel>

    companion object {

        @Provides
        fun provideState(): State {
            return State(query = "")
        }

        @Provides
        fun providePagerScreens(): List<() -> Fragment> {
            return listOf(
                { FeedScreen() },
                { SeenScreen() }
            )
        }

        @Provides
        fun Fragment.provideTabsData(): List<BottomTab> {
            return listOf(
                BottomTab(
                    icon = readDrawable(resId = R.drawable.ic_feed),
                    name = readText(resId = R.string.feed)
                ),
                BottomTab(
                    icon = readDrawable(resId = R.drawable.ic_seen),
                    name = readText(resId = R.string.seen)
                )
            )
        }
    }
}
