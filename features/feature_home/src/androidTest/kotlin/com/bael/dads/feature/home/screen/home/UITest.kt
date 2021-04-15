package com.bael.dads.feature.home.screen.home

import com.bael.dads.feature.home.R
import com.bael.dads.lib.instrumentation.fragment.BaseFragmentTest
import com.bael.dads.lib.presentation.ext.readText
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {

    override fun setupTest() {}

    @Test
    fun verifyViewsShownProperly() {
        runTest {
            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            isDisplayed(id = R.id.logoIcon)

            isDisplayed(id = R.id.settingsIcon)

            isDisplayed(text = context.readText(R.string.feed))

            isDisplayed(text = context.readText(R.string.seen))
        }
    }

    override fun clearTest() {}
}
