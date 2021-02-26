package com.bael.dads.lib.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bael.dads.lib.presentation.state.StateStore
import com.bael.dads.lib.presentation.state.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseViewModel<S>(
    initState: S,
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val key: String
        get() = javaClass.name

    private val store: Store<S> = StateStore(
        initState = restoreState() ?: initState
    )

    internal val stateFlow: Flow<S>
        get() = store.stateFlow.onEach(::saveState)

    protected val state: S
        get() = store.stateFlow.value

    private fun restoreState(): S? {
        return savedStateHandle.get(key)
    }

    private fun saveState(state: S) {
        savedStateHandle.set(key, state)
    }

    protected fun intent(sideEffect: S) {
        store.process(sideEffect)
    }
}
