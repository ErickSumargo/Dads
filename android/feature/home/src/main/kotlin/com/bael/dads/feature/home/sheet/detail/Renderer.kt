package com.bael.dads.feature.home.sheet.detail

import com.bael.dads.annotation.RenderWith
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.renderer.BaseRenderer

/**
 * Created by ErickSumargo on 01/01/21.
 */

@RenderWith(State::class)
internal interface Renderer : BaseRenderer {

    fun renderDetail(dadJoke: DadJoke?)
}
