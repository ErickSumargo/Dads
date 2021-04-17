package com.bael.dads.feature.home.screen.seen

import com.bael.dads.feature.home.R
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.entity.DadJoke
import com.bael.dads.lib.instrumentation.fragment.BaseFragmentTest
import com.bael.dads.lib.presentation.ext.readText
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {
    @Inject
    lateinit var database: DadsDatabase

    override fun setupTest() {}

    @Test
    fun givenEmptySeenDadJokes_thenEmptyStateShouldShow() {
        runTest {
            // given
            database.dadJoke.insertDadJokes(
                dadJokes = listOf()
            )

            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            isDisplayed(text = context.readText(R.string.no_data_description))
        }
    }

    @Test
    fun givenSeenDadJokes_dadJokesShouldShow() {
        runTest {
            // given
            database.dadJoke.insertDadJokes(
                dadJokes = listOf(
                    DadJoke(
                        id = 1,
                        jokeId = "1",
                        setup = "Setup 1",
                        punchline = "Punchline 1",
                        favored = false,
                        seen = true,
                        createdAt = 0L,
                        updatedAt = 0L
                    ),
                    DadJoke(
                        id = 2,
                        jokeId = "2",
                        setup = "Setup 2",
                        punchline = "Punchline 2",
                        favored = false,
                        seen = true,
                        createdAt = 0L,
                        updatedAt = 0L
                    )
                )
            )

            // when
            launch<UI>(graphResId = R.navigation.nav_graph)

            // then
            isDisplayed(text = "Setup 1")

            isDisplayed(text = "Setup 2")
        }
    }

    override fun clearTest() {
        database.closeConnection()
    }
}
