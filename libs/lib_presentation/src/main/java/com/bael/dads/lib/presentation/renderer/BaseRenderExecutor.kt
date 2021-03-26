@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.renderer

import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.scan

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseRenderExecutor<S>(
    renderer: BaseRenderer,
    private val viewModel: BaseViewModel<S>
) : BaseRenderer by renderer {

    fun observeState() {
        viewModel.stateFlow
            .flowWithLifecycle(lifecycle, minActiveState = RESUMED)
            .scan(null as S, ::dispatch)
            .launchIn(scope = lifecycleScope)
    }

    private fun dispatch(oldState: S?, newState: S): S {
        render(oldState, newState)
        return newState
    }

    protected abstract fun render(oldState: S?, newState: S)
}
