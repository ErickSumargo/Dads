@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.library.presentation.event

import com.bael.dads.library.presentation.store.Store
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class EventStore<E> : Store<E> {
    override val stateFlow: MutableStateFlow<E> = MutableStateFlow(value = null as E)

    override fun process(state: E) {
        stateFlow.value = state
    }
}
