package com.bael.dads.feature.home.sheet.detail

import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal data class State(
    val dadJoke: DadJoke?
) : BaseState()
