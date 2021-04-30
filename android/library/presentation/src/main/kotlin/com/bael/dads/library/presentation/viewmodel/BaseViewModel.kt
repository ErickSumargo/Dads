package com.bael.dads.library.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bael.dads.library.presentation.event.EventStore
import com.bael.dads.library.presentation.state.StateStore
import com.bael.dads.library.presentation.store.Store
import com.bael.dads.library.threading.Thread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseViewModel<S, E>(
    initState: S,
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    protected lateinit var thread: Thread

    private val key: String
        get() = javaClass.name

    private val stateStore: Store<S> = StateStore(
        initState = restoreState() ?: initState
    )

    private val eventStore: Store<E> = EventStore()

    internal val stateFlow: Flow<S>
        get() = stateStore.stateFlow.onEach(::saveState)

    internal val eventFlow: Flow<E>
        get() = eventStore.stateFlow.filterNotNull()

    protected val state: S
        get() = (stateStore.stateFlow as StateFlow).value

    private fun restoreState(): S? {
        return savedStateHandle.get(key)
    }

    private fun saveState(state: S) {
        savedStateHandle.set(key, state)
    }

    protected fun render(newState: S) {
        stateStore.process(newState)
    }

    protected fun action(newEvent: E) {
        eventStore.process(newEvent)
    }
}
