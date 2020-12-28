package com.bael.dads.feature.sample.screen.sample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.domain.interactor.SampleInteractor
import com.bael.dads.lib.presentation.ext.reduce
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.hilt.Assisted as HiltAssisted

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
class ViewModel @ViewModelInject constructor(
    initState: State,
    @HiltAssisted savedStateHandle: SavedStateHandle,
    private val interactor: SampleInteractor
) : BaseViewModel<State>(initState, savedStateHandle) {

    fun incrementVersion() {
        val sideEffect = state.reduce { copy(version = version + 1) }
        intent(sideEffect)
    }
}
