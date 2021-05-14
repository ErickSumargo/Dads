package com.bael.dads.feature.home.sheet.detail

import androidx.core.os.bundleOf
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.R
import com.bael.dads.library.instrumentation.matcher.MatcherParams
import com.bael.dads.library.instrumentation.sheet.BaseSheetTest
import com.bael.dads.shared.time.DateTime.now
import dagger.hilt.android.testing.HiltAndroidTest
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
            launch<UI>(args = bundleOf("dadJoke" to dadJoke))

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    text = "Setup 1"
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    text = "Punchline 1"
                )
            )
        }
    }

    @Test
    fun whenShareClicked_sharePreviewShouldShow() {
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

            clickView(
                params = MatcherParams(
                    id = R.id.shareIcon
                )
            )

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    id = R.id.shareText
                )
            )
        }
    }

    override fun clearTest() {}
}
