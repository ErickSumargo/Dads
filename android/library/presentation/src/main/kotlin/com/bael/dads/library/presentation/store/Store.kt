package com.bael.dads.library.presentation.store

import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface Store<S> {
    val stateFlow: Flow<S>

    fun process(state: S)
}
