package com.bael.dads.feature.home.screen.seen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bael.dads.data.database.entity.DadJoke
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.screen.HomeScreenTest
import com.bael.dads.library.presentation.ext.readText
import com.bael.dads.shared.time.DateTime.now
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

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
    fun givenEmptySeenDadJokes_thenEmptyStateShouldShow() = test(
        onRun = { context, rule ->
            rule.onNodeWithContentDescription(
                label = "Tab ${context.readText(resId = R.string.seen)}"
            ).performClick()

            rule.onNodeWithText(
                text = context.readText(resId = R.string.no_data_description)
            ).assertExists()
        }
    )

    @Test
    fun givenSeenDadJokes_dadJokesShouldShow() = test(
        onSetup = {
            dadJokeRepository.insertDadJokes(
                dadJokes = listOf(
                    DadJoke(
                        id = 1,
                        jokeId = "1",
                        setup = "Setup 1",
                        punchline = "Punchline 1",
                        favored = false,
                        seen = true,
                        createdAt = now,
                        updatedAt = now
                    ),
                    DadJoke(
                        id = 2,
                        jokeId = "2",
                        setup = "Setup 2",
                        punchline = "Punchline 2",
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

            rule.onAllNodes(hasText("Setup 1")).assertCountEquals(1)
            rule.onAllNodes(hasText("Setup 2")).assertCountEquals(1)
        },
        onDispose = {
            dadJokeRepository.deleteAllDadJokes()
        }
    )
}
