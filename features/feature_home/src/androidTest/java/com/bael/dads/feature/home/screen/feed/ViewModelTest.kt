package com.bael.dads.feature.home.screen.feed

import app.cash.turbine.test
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.api.test.service.TestableService
import com.bael.dads.lib.data.response.Response.Empty
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Loading
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.database.test.DadsTestableDatabase
import com.bael.dads.lib.domain.repository.DadsRepository
import com.bael.dads.lib.presentation.test.viewmodel.BaseViewModelTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject
import com.bael.dads.lib.api.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltAndroidTest
class ViewModelTest : BaseViewModelTest() {
    @Inject
    lateinit var api: TestableService<DadJokesResponse>

    @Inject
    lateinit var database: DadsTestableDatabase

    @Inject
    lateinit var repository: DadsRepository

    private lateinit var viewModel: ViewModel

    override fun setupBeforeEachTest() {
        viewModel = ViewModel(
            initState = State(responses = listOf()),
            savedStateHandle,
            repository
        ).also { viewModel ->
            viewModel.thread = thread
        }
    }

    @Test
    fun loadDadJokeFeed_with_available_response_data() {
        runBlocking {
            // given
            submitDadJokeSuccessResponse()

            // when
            viewModel.loadDadJokeFeed(cursor = null, limit = 10)

            // then
            viewModel.stateTestableFlow
                .test {
                    assertThat(
                        expectItem().responses.any { it is Loading }
                    ).isTrue()

                    assertThat(
                        expectItem().responses.any { it is Success }
                    ).isTrue()

                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

    @Test
    fun loadDadJokeFeed_with_empty_data_response() {
        runBlocking {
            // given
            submitDadJokeSuccessWithEmptyDataResponse()

            // when
            viewModel.loadDadJokeFeed(cursor = null, limit = 10)

            // then
            viewModel.stateTestableFlow
                .test {
                    assertThat(
                        expectItem().responses.any { it is Loading }
                    ).isTrue()

                    assertThat(
                        expectItem().responses.any { it is Empty }
                    ).isTrue()

                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

    @Test
    fun loadDadJokeFeed_with_error_response() {
        runBlocking {
            // given
            submitDadJokeErrorResponse()

            // when
            viewModel.loadDadJokeFeed(cursor = null, limit = 10)

            // then
            viewModel.stateTestableFlow
                .test {
                    assertThat(
                        expectItem().responses.any { it is Loading }
                    ).isTrue()

                    assertThat(
                        expectItem().responses.any { it is Error }
                    ).isTrue()

                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

    // TODO: Complete the rest test cases.

    private fun submitDadJokeSuccessResponse() {
        val dadJokes = (1..10).map { id ->
            DadJokeRemote(
                id = "$id",
                setup = "Setup $id",
                punchline = "Punchline $id"
            )
        }
        val response = DadJokesResponse(dadJokes, cursor = null)

        api.submitResponse(response = Success(response))
    }

    private fun submitDadJokeSuccessWithEmptyDataResponse() {
        val dadJokes = listOf<DadJokeRemote>()
        val response = DadJokesResponse(dadJokes, cursor = null)

        api.submitResponse(response = Success(response))
    }

    private fun submitDadJokeErrorResponse() {
        val exception = Exception()
        api.submitResponse(response = Error(exception))
    }

    override fun clearAfterEachTest() {
        api.clear()
        database.close()
    }
}
