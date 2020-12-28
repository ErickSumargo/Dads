@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.renderer

import androidx.lifecycle.lifecycleScope
import com.bael.dads.lib.presentation.ui.BaseComponent
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.scan

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
abstract class BaseRenderer<S>(
    component: BaseComponent,
    private val viewModel: BaseViewModel<S>
) : BaseComponent by component {

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
