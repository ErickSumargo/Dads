package com.bael.dads.feature.home.screen.seen

import com.bael.dads.data.database.entity.DadJoke
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.feature.home.R
import com.bael.dads.library.instrumentation.matcher.MatcherParams
import com.bael.dads.shared.time.DateTime.now
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {
    @Inject
    lateinit var dadJokeRepository: DadJokeRepository

    override fun setupTest() {}

    @Test
    fun givenEmptySeenDadJokes_thenEmptyStateShouldShow() {
        runTest {
            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    text = context.readText(resId = R.string.no_data_description)
                )
            )
        }
    }

    @Test
    fun givenSeenDadJokes_dadJokesShouldShow() {
        runTest {
            // given
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

            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    text = "Setup 1"
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    text = "Setup 2"
                )
            )
        }
    }

    override fun clearTest() {
        runBlocking {
            dadJokeRepository.deleteAllDadJokes()
        }
    }
}
