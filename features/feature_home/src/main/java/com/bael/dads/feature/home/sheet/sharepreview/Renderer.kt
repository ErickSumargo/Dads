package com.bael.dads.feature.home.sheet.sharepreview

import com.bael.dads.annotation.Render
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.renderer.BaseRenderer

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Render(State::class)
internal interface Renderer : BaseRenderer {

    fun renderPreview(dadJoke: DadJoke?)
}
