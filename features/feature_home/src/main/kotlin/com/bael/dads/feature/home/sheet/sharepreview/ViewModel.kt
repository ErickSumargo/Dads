package com.bael.dads.feature.home.sheet.sharepreview

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.domain.home.model.DadJoke
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
) : BaseViewModel<State, Event>(initState, savedStateHandle) {

    fun receiveDadJoke() {
        val dadJoke = savedStateHandle.get<DadJoke>("dadJoke")
        val newState = state.reduce {
            copy(dadJoke = dadJoke)
        }
        render(newState)
    }
}
