package com.bael.dads.feature.home.screen.feed

import com.bael.dads.annotation.RenderWith
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.renderer.BaseRenderer
import com.bael.dads.shared.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

@RenderWith(State::class)
internal interface Renderer : BaseRenderer {

    fun renderDadJokeFeed(responses: List<Response<List<DadJoke>>>)
}
