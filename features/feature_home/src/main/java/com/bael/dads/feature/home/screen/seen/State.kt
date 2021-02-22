package com.bael.dads.feature.home.screen.seen

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal data class State(
    val responses: List<Response<List<DadJoke>>>,
    val isFavoriteFilterActivated: Boolean,
) : BaseState()
