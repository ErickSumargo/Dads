package com.bael.dads.feature.home.screen.highlight

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.hilt.Assisted as HiltAssisted

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
class ViewModel @ViewModelInject constructor(
    initState: State,
    @HiltAssisted savedStateHandle: SavedStateHandle
) : BaseViewModel<State>(initState, savedStateHandle)
