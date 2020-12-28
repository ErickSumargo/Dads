package com.bael.lib.presentation.renderer

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface RendererFactory<C, VM> {

    fun init(component: C, viewModel: VM)
}
