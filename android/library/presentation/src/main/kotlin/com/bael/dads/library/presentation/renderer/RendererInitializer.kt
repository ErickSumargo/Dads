package com.bael.dads.library.presentation.renderer

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface RendererInitializer<R, VM> {

    fun init(renderer: R, viewModel: VM)
}
