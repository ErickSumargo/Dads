@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.renderer

import androidx.lifecycle.lifecycleScope
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.scan

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseRenderExecutor<S>(
    renderer: BaseRenderer,
    private val viewModel: BaseViewModel<S>
) : BaseRenderer by renderer {

    fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.stateFlow.scan(null as S, ::dispatch).collect()
        }
    }

    private fun dispatch(oldState: S?, newState: S): S {
        render(oldState, newState)
        return newState
    }

    protected abstract fun render(oldState: S?, newState: S)
}
