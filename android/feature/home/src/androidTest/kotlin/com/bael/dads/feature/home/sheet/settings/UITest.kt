package com.bael.dads.feature.home.sheet.settings

import com.bael.dads.feature.home.R
import com.bael.dads.library.instrumentation.sheet.BaseSheetTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/05/21.
 */

@HiltAndroidTest
internal class UITest : BaseSheetTest() {

    override fun setupTest() {}

    @Test
    fun assertSettingsNotchIndicatorShouldDisplay() {
        runTest {
            // when
            launch<UI>()

            // then
            isDisplayed(id = R.id.notchView)
        }
    }

    override fun clearTest() {}
}
