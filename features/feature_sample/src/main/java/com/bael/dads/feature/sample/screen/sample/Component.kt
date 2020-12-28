package com.bael.dads.feature.sample.screen.sample

import com.bael.dads.annotation.Render
import com.bael.dads.lib.presentation.ui.BaseComponent

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Render(State::class)
interface Component : BaseComponent {

    fun renderVersion(version: Int)

    fun renderIncrementer()
}
