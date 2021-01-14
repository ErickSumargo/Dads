package com.bael.dads.feature.home.screen.highlight

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bael.dads.feature.home.databinding.HighlightLayoutBinding
import com.bael.dads.feature.home.databinding.HighlightLayoutBinding.inflate
import com.bael.dads.lib.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class UI :
    BaseFragment<HighlightLayoutBinding, Renderer, ViewModel>(),
    Renderer {

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HighlightLayoutBinding {
        return inflate(inflater, container, false)
    }
}
