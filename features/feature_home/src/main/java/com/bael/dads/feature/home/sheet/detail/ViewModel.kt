package com.bael.dads.feature.home.sheet.detail

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<State>(initState, savedStateHandle) {

    fun setDadJokeFavored(favored: Boolean) {
        val sideEffect = state.reduce {
            copy(dadJokeFavored = favored)
        }
        intent(sideEffect)
    }

    fun isDadJokeFavored(): Boolean {
        return state.dadJokeFavored
    }
}
