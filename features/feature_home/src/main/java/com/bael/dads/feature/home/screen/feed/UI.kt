package com.bael.dads.feature.home.screen.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bael.dads.feature.home.databinding.FeedLayoutBinding
import com.bael.dads.feature.home.databinding.FeedLayoutBinding.inflate
import com.bael.dads.lib.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class UI :
    BaseFragment<FeedLayoutBinding, Component, ViewModel, Renderer>(),
    Component {

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FeedLayoutBinding = inflate(inflater, container, false)
}
