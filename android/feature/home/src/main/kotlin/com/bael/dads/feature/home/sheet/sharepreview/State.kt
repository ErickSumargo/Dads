package com.bael.dads.feature.home.sheet.sharepreview

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal data class State(
    val dadJoke: DadJoke?
) : BaseState()
