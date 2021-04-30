package com.bael.dads.feature.home.sheet.settings

import androidx.lifecycle.SavedStateHandle
import com.bael.dads.library.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<State, Event>(initState, savedStateHandle)
