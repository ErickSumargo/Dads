package com.bael.dads.feature.home.screen.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bael.dads.domain.common.ext.findSuccess
import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.common.response.Response.Empty
import com.bael.dads.domain.common.response.Response.Error
import com.bael.dads.domain.common.response.Response.Loading
import com.bael.dads.domain.common.response.Response.Success
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle,
    private val loadDadJokeFeedUseCase: LoadDadJokeFeedUseCase,
    private val observeDadJokeUseCase: ObserveDadJokeUseCase,
    private val setDadJokeSeenUseCase: SetDadJokeSeenUseCase,
    private val favorDadJokeUseCase: FavorDadJokeUseCase,
) : BaseViewModel<State, Event>(initState, savedStateHandle) {
    private var loadDadJokeFeedJob: Job? = null

    fun loadDadJokeFeed(cursor: DadJoke?, limit: Int) {
        loadDadJokeFeedJob?.cancel()
        loadDadJokeFeedJob = loadDadJokeFeedUseCase(cursor, limit)
            .flowOn(context = thread.io)
            .onEach(::renderResponse)
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    suspend fun observeDadJoke(dadJoke: DadJoke) {
        observeDadJokeUseCase(dadJoke)
            .flowOn(context = thread.io)
            .filter { response ->
                response is Success
            }.map { response ->
                (response as Success).data
            }.onEach(::renderUpdatedDadJoke)
            .flowOn(context = thread.default)
            .collect()
    }

    fun setDadJokeSeen(dadJoke: DadJoke) {
        setDadJokeSeenUseCase(dadJoke)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    fun favorDadJoke(dadJoke: DadJoke, favored: Boolean) {
        favorDadJokeUseCase(dadJoke, favored)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    private fun renderResponse(response: Response<List<DadJoke>>) {
        val newState = state.reduce {
            when (response) {
                is Loading -> copy(
                    responses = responses.dropLastWhile {
                        it is Error || it is Empty
                    } + response
                )
                is Error -> copy(
                    responses = responses.dropLastWhile {
                        it is Loading
                    } + response
                )
                is Empty -> copy(
                    responses = responses.dropLastWhile {
                        it is Loading
                    } + response
                )
                is Success -> copy(
                    responses = listOf(
                        Success(data = responses.findSuccess()?.data.orEmpty() + response.data)
                    )
                )
            }
        }
        render(newState)
    }

    private fun renderUpdatedDadJoke(dadJoke: DadJoke) {
        val data = state.responses.findSuccess()?.data.orEmpty().toMutableList()
        val index = data.indexOfFirst { item -> item.id == dadJoke.id }

        if (index == -1 || data[index] == dadJoke) return
        data[index] = dadJoke

        val newState = state.reduce {
            copy(responses = listOf(
                Empty.takeIf { data.isEmpty() } ?: Success(data)
            ))
        }
        render(newState)
    }
}
