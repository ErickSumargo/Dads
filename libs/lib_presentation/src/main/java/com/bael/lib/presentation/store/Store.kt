package com.bael.lib.presentation.store

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Store<S> {
    val stateFlow: StateFlow<S>

    fun process(state: S)
}
