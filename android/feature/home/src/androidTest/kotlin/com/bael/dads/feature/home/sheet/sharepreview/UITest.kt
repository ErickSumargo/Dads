package com.bael.dads.feature.home.sheet.sharepreview

import androidx.core.os.bundleOf
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.instrumentation.sheet.BaseSheetTest
import com.bael.dads.shared.time.DateTime.now
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/05/21.
 */

@HiltAndroidTest
internal class UITest : BaseSheetTest() {

    override fun setupTest() {}

    @Test
    fun assertShareMaterialShouldDisplay() {
        runTest {
            // given
            val dadJoke = DadJoke(
                id = 1,
                setup = "Setup 1",
                punchline = "Punchline 1",
                favored = false,
                seen = false,
                updatedAt = now
            )

            // when
            launch<UI>(args = bundleOf("dadJoke" to dadJoke))

            // then
            isDisplayed(text = "Setup 1")

            isDisplayed(text = "Punchline 1")
        }
    }

    override fun clearTest() {}
}
