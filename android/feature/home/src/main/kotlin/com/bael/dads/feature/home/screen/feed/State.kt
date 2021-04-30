package com.bael.dads.feature.home.screen.feed

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.state.BaseState
import com.bael.dads.shared.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal data class State(
    val responses: List<Response<List<DadJoke>>>
) : BaseState()
