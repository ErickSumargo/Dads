package com.bael.dads.feature.home.worker

import androidx.work.ListenableWorker.Result.retry
import com.bael.dads.data.database.entity.DadJoke
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.feature.home.worker.factory.FakeFetchDadJokeFeedWorkerFactory
import com.bael.dads.library.instrumentation.worker.BaseWorkerTest
import com.bael.dads.shared.time.DateTime.now
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

/**
 * Created by ErickSumargo on 15/05/21.
 */

@HiltAndroidTest
internal class FetchDadJokeFeedWorkerTest : BaseWorkerTest<FakeFetchDadJokeFeedWorkerFactory>() {
    @Inject
    lateinit var dadJokeRepository: DadJokeRepository

    override fun setupTest() {}

    @Test
    fun givenEmptyDadJoke_whenWorkerStarts_thenWorkerShouldRetryAfterwards() {
        runTest {
            // given
            val worker = createWorker<FetchDadJokeFeedWorker>()

            // when
            val result = worker.doWork()

            // then
            assertThat(result).isEqualTo(retry())
        }
    }

    @Test
    fun givenDadJokeFeed_whenWorkerStarts_thenNotificationShouldShow() {
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
                        seen = false,
                        createdAt = now,
                        updatedAt = now
                    )
                )
            )

            val worker = createWorker<FetchDadJokeFeedWorker>()

            // when
            worker.doWork()

            // then
            // TODO
        }
    }

    override fun clearTest() {
        runBlocking {
            dadJokeRepository.deleteAllDadJokes()
        }
    }
}
