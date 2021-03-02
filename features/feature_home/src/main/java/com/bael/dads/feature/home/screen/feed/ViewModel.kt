package com.bael.dads.feature.home.screen.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bael.dads.lib.data.ext.findSuccess
import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Empty
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Loading
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.domain.repository.DadsRepository
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
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
    private val repository: DadsRepository
) : BaseViewModel<State>(initState, savedStateHandle) {
    private var loadDadJokeFeedJob: Job? = null

    fun loadDadJokeFeed(cursor: DadJoke?, limit: Int) {
        loadDadJokeFeedJob?.cancel()
        loadDadJokeFeedJob = repository.loadDadJokeFeed(cursor, limit)
            .flowOn(context = IO)
            .onEach(::intentResponse)
            .flowOn(context = Default)
            .launchIn(scope = viewModelScope)
    }

    suspend fun observeDadJoke(dadJoke: DadJoke) {
        repository.observeDadJoke(dadJoke)
            .flowOn(context = IO)
            .filter { response ->
                response is Success
            }.map { response ->
                (response as Success).data
            }.onEach(::intentUpdatedDadJoke)
            .flowOn(context = Default)
            .collect()
    }

    fun setDadJokeSeen(dadJoke: DadJoke) {
        repository.setDadJokeSeen(dadJoke)
            .flowOn(context = IO)
            .launchIn(scope = viewModelScope)
    }

    fun favorDadJoke(dadJoke: DadJoke, favored: Boolean) {
        repository.favorDadJoke(dadJoke, favored)
            .flowOn(context = IO)
            .launchIn(scope = viewModelScope)
    }

    private fun intentResponse(response: Response<List<DadJoke>>) {
        val sideEffect = state.reduce {
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
        intent(sideEffect)
    }

    private fun intentUpdatedDadJoke(dadJoke: DadJoke) {
        val data = state.responses.findSuccess()?.data.orEmpty().toMutableList()
        val index = data.indexOfFirst { item -> item.id == dadJoke.id }

        if (index == -1 || data[index] == dadJoke) return
        data[index] = dadJoke

        val sideEffect = state.reduce {
            copy(responses = listOf(
                Empty.takeIf { data.isEmpty() } ?: Success(data)
            ))
        }
        intent(sideEffect)
    }
}
