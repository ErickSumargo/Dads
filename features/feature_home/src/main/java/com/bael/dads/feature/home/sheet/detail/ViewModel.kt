package com.bael.dads.feature.home.sheet.detail

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.domain.model.DadJoke
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
    val dadJoke: DadJoke?
        get() = state.dadJoke

    fun receiveDadJoke() {
        val dadJoke = savedStateHandle.get<DadJoke>("dadJoke")
        val sideEffect = state.reduce {
            copy(dadJoke = dadJoke)
        }
        intent(sideEffect)
    }

    fun favorDadJoke(favored: Boolean) {
        val sideEffect = state.reduce {
            copy(dadJoke = dadJoke?.copy(favored = favored))
        }
        intent(sideEffect)
    }
}
