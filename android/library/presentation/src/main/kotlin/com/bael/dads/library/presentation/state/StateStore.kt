package com.bael.dads.library.presentation.state

import com.bael.dads.library.presentation.store.Store
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class StateStore<S>(initState: S) : Store<S> {
    override val stateFlow: MutableStateFlow<S> = MutableStateFlow(value = initState)

    override fun process(state: S) {
        stateFlow.value = state
    }
}
