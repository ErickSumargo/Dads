package com.bael.dads.feature.home.screen.sharepreview

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.screen.HomeScreenTest
import com.bael.dads.library.presentation.ext.readText
import com.bael.dads.shared.time.DateTime.now
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB

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

    @Inject
    lateinit var dadJokeRepository: DadJokeRepository

    @Test
    fun whenSheetShown_settingsMenuShouldShow() = test(
        onSetup = {
            dadJokeRepository.insertDadJokes(
                dadJokes = listOf(
                    DadJokeDB(
                        id = 1,
                        jokeId = "1",
                        setup = "Setup 1",
                        punchline = "Punchline 1",
                        favored = false,
                        seen = true,
                        createdAt = now,
                        updatedAt = now
                    )
                )
            )
        },
        onRun = { context, rule ->
            rule.onNodeWithContentDescription(
                label = "Tab ${context.readText(resId = R.string.seen)}"
            ).performClick()

            rule.onNodeWithText(text = "Setup 1").performClick()

            rule.onNodeWithContentDescription(
                label = "Icon ${context.readText(resId = R.string.share)}"
            ).performClick()

            rule.onNodeWithText(
                text = context.readText(resId = R.string.preview)
            ).assertExists()
            rule.onNodeWithText(
                text = context.readText(resId = R.string.share)
            ).assertExists()
        },
        onDispose = {
            dadJokeRepository.deleteAllDadJokes()
        }
    )
}
