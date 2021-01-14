@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bael.dads.lib.presentation.ext.invoke
import com.bael.dads.lib.presentation.renderer.RendererInitializer
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
abstract class BaseFragment<VB : ViewBinding, R, VM : BaseViewModel<*>> : Fragment() {
    @Inject
    internal lateinit var rendererInitializer: RendererInitializer<R, VM>

    @Inject
    protected lateinit var viewModel: @JvmSuppressWildcards Lazy<VM>

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initRenderer()

        _viewBinding = bindView(inflater, container)
        return _viewBinding?.root
    }

    private fun initRenderer() {
        rendererInitializer.init(
            renderer = this as R,
            viewModel = viewModel()
        )
    }

    abstract fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
