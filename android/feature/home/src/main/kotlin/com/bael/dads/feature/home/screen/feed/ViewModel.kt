package com.bael.dads.feature.home.screen.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker.Companion.INPUT_CURSOR_ID
import com.bael.dads.library.threading.Thread
import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Empty
import com.bael.dads.shared.response.Response.Error
import com.bael.dads.shared.response.Response.Loading
import com.bael.dads.shared.response.Response.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker.Companion.TAG as FetchDadJokeFeedWorkerTag

/**
 * Created by ErickSumargo on 01/11/21.
 */

@HiltViewModel
internal class FeedViewModel @Inject constructor(
    val thread: Thread,
    private val workManager: WorkManager,
    private val loadDadJokeFeedUseCase: LoadDadJokeFeedUseCase,
    private val setDadJokeSeenUseCase: SetDadJokeSeenUseCase,
    private val favorDadJokeUseCase: FavorDadJokeUseCase
) : ViewModel() {
    private val _feed: MutableStateFlow<List<Response<List<DadJoke>>>> =
        MutableStateFlow(listOf())

    val feed: StateFlow<List<Response<List<DadJoke>>>>
        get() = _feed

    private var loadDadJokeFeedJob: Job? = null

    fun loadDadJokeFeed(cursor: DadJoke?, limit: Int) {
        loadDadJokeFeedJob?.cancel()
        loadDadJokeFeedJob = loadDadJokeFeedUseCase(cursor, limit)
            .flowOn(context = thread.io)
            .onEach(::mapResponse)
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    fun setDadJokeSeen(dadJoke: DadJoke) {
        setDadJokeSeenUseCase(dadJoke)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    fun likeDadJoke(dadJoke: DadJoke, favored: Boolean) {
        favorDadJokeUseCase(dadJoke, favored)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    fun scheduleFeedWorker(cursor: DadJoke?) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = workDataOf(
            INPUT_CURSOR_ID to cursor?.id
        )

        val workRequest = PeriodicWorkRequestBuilder<FetchDadJokeFeedWorker>(
            repeatInterval = 30,
            repeatIntervalTimeUnit = TimeUnit.MINUTES,
            flexTimeInterval = 15,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        workManager.enqueueUniquePeriodicWork(
            FetchDadJokeFeedWorkerTag,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun mapResponse(response: Response<List<DadJoke>>) {
        _feed.update { feed ->
            when (response) {
                is Loading -> feed.dropLastWhile { it is Error || it is Empty }
                is Error -> feed.dropLastWhile { it is Loading }
                is Empty -> feed.dropLastWhile { it is Loading }
                is Success -> feed.dropLastWhile { it is Loading }
            } + response
        }
    }
}
