package com.bael.dads.feature.home.screen.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.screen.HomeScreenTest
import com.bael.dads.library.presentation.ext.readText
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltAndroidTest
internal class ScreenTest : HomeScreenTest() {
    override val route: String = "/"

    @Test
    fun whenSheetShown_settingsMenuShouldShow() = test(
        onRun = { context, rule ->
            rule.onNodeWithContentDescription(
                label = "Icon ${context.readText(resId = R.string.settings)}"
            ).performClick()

            rule.onNodeWithText(
                text = context.readText(resId = R.string.notification)
            ).assertExists()

            rule.onNodeWithText(
                text = context.readText(resId = R.string.theme)
            ).assertExists()
        }
    )
}
