package com.bael.dads.feature.home.sheet.detail

import androidx.core.os.bundleOf
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.instrumentation.sheet.BaseSheetTest
import com.bael.dads.shared.time.DateTime.now
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

/**
 * Created by ErickSumargo on 15/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseSheetTest() {

    override fun setupTest() {}

    @Test
    fun assertDadJokeShouldDisplay() {
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
            launch<UI>(args = bundleOf("dadJoke" to Json.encodeToString(dadJoke)))

            // then
            isDisplayed(text = "Setup 1")

            isDisplayed(text = "Punchline 1")
        }
    }

    override fun clearTest() {}
}
