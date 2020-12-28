package com.bael.dads.feature.sample.screen.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bael.dads.feature.sample.databinding.SampleLayoutBinding
import com.bael.dads.feature.sample.databinding.SampleLayoutBinding.inflate
import com.bael.dads.lib.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class UI :
    BaseFragment<SampleLayoutBinding, Component, ViewModel, Renderer>(),
    Component {

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SampleLayoutBinding = inflate(inflater, container, false)

    override fun renderVersion(version: Int) {
        viewBinding.version.also { label ->
            label.text = "Version: $version"
        }
    }

    override fun renderIncrementer() {
        viewBinding.incrementer.also { button ->
            button.text = "Incrementer"
            button.setOnClickListener {
                viewModel.incrementVersion()
            }
        }
    }
}
