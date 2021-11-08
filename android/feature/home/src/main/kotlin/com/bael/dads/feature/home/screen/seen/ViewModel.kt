package com.bael.dads.feature.home.screen.seen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadFavoredDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadSeenDadJokeUseCase
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
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/11/21.
 */

@HiltViewModel
internal class SeenViewModel @Inject constructor(
    val thread: Thread,
    private val loadSeenDadJokeUseCase: LoadSeenDadJokeUseCase,
    private val loadFavoredDadJokeUseCase: LoadFavoredDadJokeUseCase,
    private val favorDadJokeUseCase: FavorDadJokeUseCase
) : ViewModel() {
    private val _seen: MutableStateFlow<List<Response<List<DadJoke>>>> =
        MutableStateFlow(listOf())

    val seen: StateFlow<List<Response<List<DadJoke>>>>
        get() = _seen

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
                mapResponse(cursor, response)
            }
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    fun favorDadJoke(dadJoke: DadJoke, favored: Boolean) {
        favorDadJokeUseCase(dadJoke, favored)
            .flowOn(context = thread.io)
            .launchIn(scope = viewModelScope)
    }

    private fun mapResponse(
        cursor: DadJoke?,
        response: Response<List<DadJoke>>
    ) {
        _seen.update { seen ->
            if (cursor == null) {
                listOf(response)
            } else {
                when (response) {
                    is Loading -> seen.dropLastWhile { it is Error || it is Empty }
                    is Error -> seen.dropLastWhile { it is Loading }
                    is Empty -> seen.dropLastWhile { it is Loading }
                    is Success -> seen.dropLastWhile { it is Loading }
                } + response
            }
        }
    }
}
