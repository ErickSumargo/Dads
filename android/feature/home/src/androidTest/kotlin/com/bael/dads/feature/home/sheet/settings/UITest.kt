package com.bael.dads.feature.home.sheet.settings

import com.bael.dads.feature.home.R
import com.bael.dads.library.instrumentation.sheet.BaseSheetTest
import com.bael.dads.library.presentation.ext.readText
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/05/21.
 */

@HiltAndroidTest
internal class UITest : BaseSheetTest() {

    override fun setupTest() {}

    @Test
    fun assertSettingsMenuShouldDisplay() {
        runTest {
            // when
            launch<UI>()

            // then
            isDisplayed(text = context.readText(R.string.notification))
            isDisplayed(text = context.readText(R.string.new_feed_reminder))

            isDisplayed(text = context.readText(R.string.theme))
            isDisplayed(text = context.readText(R.string.night_mode))
        }
    }

    override fun clearTest() {}
}
