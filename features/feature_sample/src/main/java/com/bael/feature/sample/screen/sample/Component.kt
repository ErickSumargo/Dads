package com.bael.feature.sample.screen.sample

import com.bael.annotation.Render
import com.bael.lib.presentation.ui.BaseComponent

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Render(State::class)
interface Component : BaseComponent {

    fun renderVersion(version: Int)

    fun renderIncrementer()
}
