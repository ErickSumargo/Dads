package com.bael.dads.lib.presentation.state

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface Store<S> {
    val stateFlow: StateFlow<S>

    fun process(state: S)
}
