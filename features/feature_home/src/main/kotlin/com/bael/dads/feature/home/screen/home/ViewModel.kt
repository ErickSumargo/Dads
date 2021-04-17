package com.bael.dads.feature.home.screen.home

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<State, Event>(initState, savedStateHandle) {
    val queryFlow: MutableStateFlow<String> = MutableStateFlow(state.query)

    fun submitQuery(query: String) {
        queryFlow.value = query
        renderQuery(query)
    }

    private fun renderQuery(query: String) {
        val newState = state.reduce {
            copy(query = query)
        }
        render(newState)
    }
}
