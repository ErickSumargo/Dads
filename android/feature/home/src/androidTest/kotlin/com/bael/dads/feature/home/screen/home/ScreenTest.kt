package com.bael.dads.feature.home.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavGraphBuilder
import com.bael.dads.feature.home.navigation.homeNavigation
import com.bael.dads.library.instrumentation.screen.BaseScreenTest
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@HiltAndroidTest
internal class ScreenTest : BaseScreenTest(destination = "home") {
    override val NavGraphBuilder.navigation
        get() = homeNavigation(sheetContent = {})

    override val route: String = "/"

    @Test
    fun test() {
        test(
            onSetup = {},
            onRun = { _, rule ->
                rule.onNodeWithText(text = "Feed")
            }
        )
    }
}
