package com.bael.dads.feature.home.screen.home

import com.bael.dads.feature.home.R
import com.bael.dads.library.instrumentation.matcher.MatcherParams
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {

    override fun setupTest() {}

    @Test
    fun whenScreenDisplayed_contentShouldShow() {
        runTest {
            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    id = R.id.logoIcon
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    id = R.id.settingsIcon
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    text = context.readText(resId = R.string.feed)
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    text = context.readText(resId = R.string.seen)
                )
            )
        }
    }

    override fun clearTest() {}
}
