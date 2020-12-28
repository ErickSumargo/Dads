package com.bael.lib.presentation.store

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
class StateStore<S>(initState: S) : Store<S> {
    override val stateFlow: MutableStateFlow<S> = MutableStateFlow(value = initState)

    override fun process(state: S) {
        stateFlow.value = state
    }
}
