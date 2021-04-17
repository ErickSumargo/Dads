package com.bael.dads.feature.home.screen.seen

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
import com.bael.dads.domain.home.usecase.LoadFavoredDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadSeenDadJokeUseCase
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
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
    private val loadSeenDadJokeUseCase: LoadSeenDadJokeUseCase,
    private val loadFavoredDadJokeUseCase: LoadFavoredDadJokeUseCase,
    private val observeDadJokeUseCase: ObserveDadJokeUseCase,
    private val favorDadJokeUseCase: FavorDadJokeUseCase
) : BaseViewModel<State, Event>(initState, savedStateHandle) {
    private var loadSeenDadJokeJob: Job? = null

    fun loadSeenDadJoke(
        term: String,
        cursor: DadJoke?,
        limit: Int,
        favoredOnly: Boolean
    ) {
        loadSeenDadJokeJob?.cancel()
        loadSeenDadJokeJob = if (!favoredOnly) {
            loadSeenDadJokeUseCase(term, cursor, limit)
        } else {
            loadFavoredDadJokeUseCase(term, cursor, limit)
        }
            .flowOn(context = thread.io)
            .onEach { response ->
                renderResponse(cursor, response)
            }
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

    fun favorDadJoke(dadJoke: DadJoke, favored: Boolean) {
        favorDadJokeUseCase(dadJoke, favored)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    fun toggleFavoriteFilter(isActivated: Boolean) {
        val newState = state.reduce {
            copy(isFavoriteFilterActivated = isActivated)
        }
        render(newState)
    }

    fun isFavoriteFilterActivated(): Boolean {
        return state.isFavoriteFilterActivated
    }

    private fun renderResponse(
        cursor: DadJoke?,
        response: Response<List<DadJoke>>
    ) {
        val newState = state.reduce {
            when (response) {
                is Loading -> copy(
                    responses = listOf(response).takeIf {
                        cursor == null
                    } ?: responses.dropLastWhile {
                        it is Error || it is Empty
                    } + response
                )
                is Error -> copy(
                    responses = listOf(response).takeIf {
                        cursor == null
                    } ?: responses.dropLastWhile {
                        it is Loading
                    } + response
                )
                is Empty -> copy(
                    responses = listOf(response).takeIf {
                        cursor == null
                    } ?: responses.dropLastWhile {
                        it is Loading
                    } + response
                )
                is Success -> copy(
                    responses = listOf(response).takeIf {
                        cursor == null
                    } ?: listOf(
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
        if (state.isFavoriteFilterActivated && !dadJoke.favored) {
            data.removeAt(index)
        } else {
            data[index] = dadJoke
        }

        val newState = state.reduce {
            copy(responses = listOf(Empty.takeIf { data.isEmpty() } ?: Success(data)))
        }
        render(newState)
    }
}
