package com.bael.dads.feature.home.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<State>(initState, savedStateHandle),
    HomePresenter {
    override val queryFlow: MutableStateFlow<String> = MutableStateFlow(state.query)

    fun submitQuery(query: String) {
        viewModelScope.launch {
            queryFlow.value = query
            intentQuery(query)
        }
    }

    private fun intentQuery(query: String) {
        val sideEffect = state.reduce {
            copy(query = query)
        }
        intent(sideEffect)
    }
}
