package com.bael.dads.lib.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.NONE
import androidx.annotation.VisibleForTesting.PROTECTED
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bael.dads.lib.presentation.state.StateStore
import com.bael.dads.lib.presentation.state.Store
import com.bael.dads.lib.threading.Thread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseViewModel<S>(
    initState: S,
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    lateinit var thread: Thread
        @VisibleForTesting(otherwise = PROTECTED)
        get

    private val key: String
        get() = javaClass.name

    private val store: Store<S> = StateStore(
        initState = restoreState() ?: initState
    )

    internal val stateFlow: Flow<S>
        get() = store.stateFlow.onEach(::saveState)

    val stateTestableFlow: Flow<S>
        @VisibleForTesting(otherwise = NONE)
        get() = stateFlow

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
