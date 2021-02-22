package com.bael.dads.feature.home.screen.seen

import com.bael.dads.annotation.Render
import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.renderer.BaseRenderer

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Render(State::class)
internal interface Renderer : BaseRenderer {

    fun renderSeenDadJoke(responses: List<Response<List<DadJoke>>>)

    fun renderFavoriteFilter(isFavoriteFilterActivated: Boolean)
}
